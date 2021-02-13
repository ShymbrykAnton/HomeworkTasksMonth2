var canvas = document.getElementById("mainCanvas");
var ctx = canvas.getContext('2d');
var pos = {x: 0, y: 0};
var colorPanel = document.getElementById("ColorPanel");
var lineThickness = document.getElementById("LineThickness");
var eraser = document.getElementById("Eraser");
var saveImg = document.getElementById("Save");

document.addEventListener('mousemove', draw);
document.addEventListener('mousedown', setPosition);
document.addEventListener('mouseenter', setPosition);
eraser.addEventListener('click', clearAll);
saveImg.addEventListener('click', saveCanvasAsImageFile);

function setPosition(e) {
    pos.x = e.clientX - canvas.offsetLeft;
    pos.y = e.clientY - canvas.offsetTop;
}

function clearAll() {
    ctx.clearRect(0, 0, canvas.clientWidth + 10, canvas.clientHeight + 10)
}

function draw(e) {
    if (e.buttons !== 1) {
        return;
    }
    ctx.beginPath();
    ctx.lineWidth = lineThickness.value;
    ctx.lineCap = 'round';
    ctx.strokeStyle = colorPanel.value;
    ctx.moveTo(pos.x, pos.y);
    setPosition(e);
    ctx.lineTo(pos.x, pos.y);
    ctx.stroke();
}

function getImage() {
    var imageData = canvas.toDataURL();
    var image = new Image();
    image.src = imageData;
    return image;
}

function saveImage(image) {
    var link = document.createElement("a");
    link.setAttribute("href", image.src);
    link.setAttribute("download", "canvasImage");
    link.click();
}

function saveCanvasAsImageFile() {
    var image = getImage();
    saveImage(image);
}