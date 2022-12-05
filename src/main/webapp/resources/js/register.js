/**
 * 
 */

window.onload = function() {
	let cssClass = "ui-state-highlight";
	let tbody = document.getElementById("j_id141165066_64e8e2e4:rola_data");
	let defektuzkoa = "erabiltzailea";
	for (let elem of tbody.querySelectorAll("tr")) {
		let td = elem.querySelector("td");
		if (td.innerText == defektuzkoa) {
			td.click();
		}
	}
}