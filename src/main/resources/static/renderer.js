var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', {preload: preload, create:create, update: update});
var map;
var characterID = "a9729c92-a0ba-4d64-8b01-8022dc5eb0d0";

var stompClient = null;

// Async loading:
// http://www.html5gamedevs.com/topic/7491-async-image-loader/
// http://www.html5gamedevs.com/topic/9103-synchronous-loading-with-gameloadjson/

// Preload json data, then load images: http://examples.phaser.io/_site/view_full.html?d=loader&f=load+events.js&t=load%20events

function preload(){
    game.load.json('map_json', '/map/get?id=242eceff-3ce0-4e23-9183-a63de7cbf66e');
    game.load.image('test', 'browser_quest/img/2/tilesheet.png');
    game.load.spritesheet('sprites', 'browser_quest/img/2/coder.png', 48, 48, 2);
}

function create() {
    connect('/ws');

    parseMap();
//    parseSprites();
}

function parseSprite(character) {
//    debugger;
    game.add.sprite(character.x, character.y, 'sprites', frame=character.sprite);
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
        subscribeToTopics();
        login();
    });

    return stompClient;
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
        console.log('Disconnected');
    }
}

function subscribeToTopics() {
    stompClient.subscribe('/topic/heartbeat', function(msg) {
        logWebsocketMessage(msg);
    });

    stompClient.subscribe('/topic/client', function(msg) {
        logWebsocketMessage(msg);
    });

    stompClient.subscribe('/character/' + characterID, function(msg) {
        var msgJSON = JSON.parse(msg.body);
        parseSprite(msgJSON);
        logWebsocketMessage(msgJSON);
    });
}

function login() {
    var request = JSON.stringify({'userID':characterID});
    stompClient.send("/app/login", {}, request );
}

function logWebsocketMessage(msg) {
    console.log(msg);
}