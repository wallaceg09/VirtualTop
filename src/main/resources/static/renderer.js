var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', {preload: preload, create:create, update: update});
var map;

var stompClient = null;

// Async loading:
// http://www.html5gamedevs.com/topic/7491-async-image-loader/
// http://www.html5gamedevs.com/topic/9103-synchronous-loading-with-gameloadjson/

// Preload json data, then load images: http://examples.phaser.io/_site/view_full.html?d=loader&f=load+events.js&t=load%20events

function preload(){
    game.load.json('map_json', '/map/get?id=242eceff-3ce0-4e23-9183-a63de7cbf66e');
    game.load.image('test', 'roguelikeSheet_transparent.png');
    game.load.spritesheet('sprites', 'roguelikeChar_transparent.png', 16, 16, spacing=1);
}

function create() {
    parseMap();
    parseSprites();

    connect('/ws');
}

function parseSprites() {
    game.add.sprite(100, 100, 'sprites', 0);
}

function parseMap() {
    var map_json = game.cache.getJSON('map_json');

    var tilemap_json = map_json['json'];
    tilemap_json = JSON.parse(tilemap_json);

    game.cache.addTilemap('tilemap_json', null, tilemap_json, Phaser.Tilemap.TILED_JSON);

    map = game.add.tilemap('tilemap_json');

    var imgMap = map_json.imageMap;

    for(var key in imgMap) {
        var val = imgMap[key];
        console.log(key + ' ' + val);
        map.addTilesetImage(key, val);
    }

    var layers = tilemap_json.layers;

    for(var i = 0; i < layers.length; i++) {
        map.createLayer(layers[i].name);
    }
}

function update() {

}

function connect(endpoint) {
    var socket = new SockJS(endpoint);

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected! Frame: ' + frame);
        stompClient.subscribe('/topic/heartbeat', function(msg) {
            console.log(msg);
        });
    });

    return stompClient;
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
        console.log('Disconnected');
    }
}