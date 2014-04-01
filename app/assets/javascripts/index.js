$("#selectRealEstate").change(function() {
	alert('Hello!');
	$.getJSON("/getApartmentBuilding/" + $(this).val(), {},
		function(json) {
			for (var i = 0; i < json.length; i++) {
				$("#selectBuilding")
					.append("<option value=" + json[i].id + ">" + json[i].name + "</option>");
			}
		}
	)
});