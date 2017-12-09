function calculateIngress() {
    document.getElementById('ingress').onkeyup = function () {
        document.getElementById('ingressCount').innerHTML = "Kirjaimia jäljellä: " + (200 - this.value.length);
    }
}


function calculateContent() {
    document.getElementById('content').onkeyup = function () {
        document.getElementById('contentCount').innerHTML = "Kirjaimia jäljellä: " + (1000 - this.value.length);
    }

}