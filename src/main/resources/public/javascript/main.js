function calculateIngress() {
    document.getElementsByName('ingress').onkeyup = function () {
        document.getElementById('count').innerHTML = "Characters left: " + (500 - this.value.length);
    };
}

function calculateContent() {

}