var btn = $('#button');

$(window).scroll(function() {
  if ($(window).scrollTop() > 1400) {
    btn.addClass('show');
  } else {
    btn.removeClass('show');
  }
});

btn.on('click', function(e) {
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

$(document).ready(function() {
        $("#navbar").click(function(e) {
            var isActive = $(this).hasClass('.nav-link active');
            $('.nav-link').removeClass('active');
            if (!isActive) {
            $(this).addClass('active');
            }
        });
 });

var doing = false;
var spin = [new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3"),new Audio("sounds/spin.mp3")];
var coin = [new Audio("sounds/coin.mp3"),new Audio("sounds/coin.mp3"),new Audio("sounds/coin.mp3")]
var win = new Audio("sounds/win.mp3");
var lose = new Audio("sounds/lose.mp3");
var audio = false;
let status = document.getElementById("status")
var info = true;

function doSlot(){
	if (doing){return null;}
	doing = true;
	var numChanges = randomInt(1,4)*7
	var numeberSlot1 = numChanges+randomInt(1,7)
	var numeberSlot2 = numChanges+2*7+randomInt(1,7)
	var numeberSlot3 = numChanges+4*7+randomInt(1,7)

	var i1 = 0;
	var i2 = 0;
	var i3 = 0;
	var sound = 0
	status.innerHTML = "Η τύχη σου γυρίζει..."
	slot1 = setInterval(spin1, 50);
	slot2 = setInterval(spin2, 50);
	slot3 = setInterval(spin3, 50);
	function spin1(){
		i1++;
		if (i1>=numeberSlot1){
			coin[0].play()
			clearInterval(slot1);
			return null;
		}
		let slotTile = document.getElementById("slot1");
		if (slotTile.className=="a7"){
			slotTile.className = "a0";
		}
		slotTile.className = "a"+(parseInt(slotTile.className.substring(1))+1)
	}
	function spin2(){
		i2++;
		if (i2>=numeberSlot2){
			coin[1].play()
			clearInterval(slot2);
			return null;
		}
		let slotTile = document.getElementById("slot2");
		if (slotTile.className=="a7"){
			slotTile.className = "a0";
		}
		slotTile.className = "a"+(parseInt(slotTile.className.substring(1))+1)
	}
	function spin3(){
		i3++;
		if (i3>=numeberSlot3){
			coin[2].play()
			clearInterval(slot3);
			testWin();
			return null;
		}
		let slotTile = document.getElementById("slot3");
		if (slotTile.className=="a7"){
			slotTile.className = "a0";
		}
		sound++;
		if (sound==spin.length){
			sound=0;
		}
		spin[sound].play();
		slotTile.className = "a"+(parseInt(slotTile.className.substring(1))+1)
	}
}

function testWin(){
	var slot1 = document.getElementById("slot1").className
	var slot2 = document.getElementById("slot2").className
	var slot3 = document.getElementById("slot3").className

	if (((slot1 == slot2 && slot2 == slot3) ||
		(slot1 == slot2 && slot3 == "a7") ||
		(slot1 == slot3 && slot2 == "a7") ||
		(slot2 == slot3 && slot1 == "a7") ||
		(slot1 == slot2 && slot1 == "a7") ||
		(slot1 == slot3 && slot1 == "a7") ||
		(slot2 == slot3 && slot2 == "a7") ) && !(slot1 == slot2 && slot2 == slot3 && slot1=="a7")){
		status.innerHTML = "Κερδίσατε!";
		win.play();
	}else{
		status.innerHTML = "Χάσατε!"
		lose.play();
	}
	doing = false;
}

function toggleAudio(){
	if (!audio){
		audio = !audio;
		for (var x of spin){
			x.volume = 0.5;
		}
		for (var x of coin){
			x.volume = 0.5;
		}
		win.volume = 1.0;
		lose.volume = 1.0;
	}else{
		audio = !audio;
		for (var x of spin){
			x.volume = 0;
		}
		for (var x of coin){
			x.volume = 0;
		}
		win.volume = 0;
		lose.volume = 0;
	}
	document.getElementById("audio").src = "sounds/audio"+(audio?"On":"Off")+".png";
}

function randomInt(min, max){
	return Math.floor((Math.random() * (max-min+1)) + min);
}

var btn = $('#button');

                $(window).scroll(function() {
                  if ($(window).scrollTop() > 200) {
                    btn.addClass('show');
                  } else {
                    btn.removeClass('show');
                  }
                });

                btn.on('click', function(e) {
                  window.scrollTo({ top: 0, behavior: 'smooth' });
                });

                $(document).ready(function() {
                        $("#navbar").click(function(e) {
                            var isActive = $(this).hasClass('.nav-link active');
                            $('.nav-link').removeClass('active');
                            if (!isActive) {
                            $(this).addClass('active');
                            }
                        });
                 });
 
