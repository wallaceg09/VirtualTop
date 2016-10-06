var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', {preload: preload, create:create, update: update});
var map;

// Async loading:
// http://www.html5gamedevs.com/topic/7491-async-image-loader/
// http://www.html5gamedevs.com/topic/9103-synchronous-loading-with-gameloadjson/

function preload(){
    game.load.json('map_json', '/map/get?id=242eceff-3ce0-4e23-9183-a63de7cbf66e');
//    game.load.onFileComplete.add(jsonComplete, this);
    game.load.image('test', 'test.png');
}

function jsonComplete(progress, key, success, loaded, total) {
    if(key == 'map_json') {
        var tilemap_json = game.cache.getJSON('map_json')['json'];
        tilemap_json = JSON.parse(tilemap_json);

        game.cache.addTilemap('tilemap_json', null, tilemap_json);
    }
}

function create() {
    var map_json = game.cache.getJSON('map_json');

    var tilemap_json = map_json['json'];
    tilemap_json = JSON.parse(tilemap_json);

    game.cache.addTilemap('tilemap_json', null, tilemap_json, Phaser.Tilemap.TILED_JSON);

    debugger;

    map = game.add.tilemap('tilemap_json'); // FIXME: How do I use the json property within the map_json?

    var imgMap = map_json.imageMap;

    for(var key in imgMap) {
        var val = imgMap[key];
        console.log(key + ' ' + val);
        map.addTilesetImage(key, val);
    }

    map.createLayer('Floor');
    map.createLayer('Wall');
}

function update() {

}