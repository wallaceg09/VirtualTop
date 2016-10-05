//var map;
//var layer1;

var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', {preload: preload, create:create, update: update});

function preload(){
    game.load.spritesheet("test", "/test.png", 16, 16);
}

function create() {
    //map = game.add.tilemap();
    //layer1 = map.create("layer1", 40, 30, 16, 16)
    var layer1 = game.add.group();
    for(var y = 0; y < 38; y++) {
        for(var x = 0; x < 50; x++) {
            game.add.tileSprite(x*16, y*16, 16, 16, 'test', 50, layer1);
        }
    }

}

function update() {

}