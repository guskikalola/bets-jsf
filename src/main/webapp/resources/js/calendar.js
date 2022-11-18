function dateEquals(d1,d2) {
	let f1 = d1.getYear() == d2.getYear();
	let f2 = d1.getMonth() == d2.getMonth();
	let f3 = d1.getDate() == d2.getDate();
	
	return f1 && f2 && f3;
}

function highlightDays(dates,date) {
	console.log(dates);
	var cssclass = '';
	for (var i = 0; i < dates.length; i++) {
		let d = new Date(dates[i]);
		console.log(date,d,dateEquals(date,d))
		if (dateEquals(date,d)) {
			cssclass = 'highlight-calendar';
		}
	}
	return [true, cssclass];
}