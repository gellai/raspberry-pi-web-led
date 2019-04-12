var ws = null;

var redSlider   = document.getElementById('redSlider');
var greenSlider = document.getElementById('greenSlider');
var blueSlider  = document.getElementById('blueSlider');

function connect() {
	ws = new SockJS("/gpio-websocket");
	console.log("Connection created");

	ws.onopen = function() {
		console.log('Websocket: open');
	};

	ws.onclose = function() {
		console.log("Websocket: close")
		alert("Socket connection to GPIO is lost! \n Try to refresh the page.");
	};

	ws.onmessage = function(message) {
		try {
			var packet = eval('(' + message.data + ')');

			redSlider.value   = packet.redSlider;
			greenSlider.value = packet.greenSlider;
			blueSlider.value  = packet.blueSlider;

			document.getElementById('red-value').innerHTML   = redSlider.value;
			document.getElementById('green-value').innerHTML = greenSlider.value;
			document.getElementById('blue-value').innerHTML  = blueSlider.value;
		} catch(err) {
			console.log("Error:" + message.data);
		}
	};
}

function sendValues(jsonObj) {
	ws.send(jsonObj);
}

var colorSliders = document.getElementsByClassName("slider");

for(var i = 0; i < colorSliders.length; i++) {
	colorSliders[i].addEventListener("change", function() {
		var jsonObj = "{\"redValue\" : \"" + redSlider.value + "\","
				+ "\"greenValue\" : \"" + greenSlider.value + "\","
				+ "\"blueValue\" : \"" + blueSlider.value + "\"}";

		sendValues(jsonObj);
	});
}