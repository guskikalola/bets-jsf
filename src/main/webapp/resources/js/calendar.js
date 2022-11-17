function highlightDays(date) {
	var dates = [];
	console.log(dates);
	var cssclass = '';
	for (var i = 0; i < dates.length; i++) {
		if (date === new Date(dates[i])) {
			cssclass = 'highlight-calendar';
		}
	}
	return [true, cssclass];
}