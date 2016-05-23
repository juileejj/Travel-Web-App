 function codeAddress() {
        var address = document.getElementById("address").value;
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode( { 'address': address}, function(results, status) {
          var location = results[0].geometry.location;
          var e = location.lat()+","+location.lng();
         /* alert('LAT: ' + location.lat() + ' LANG: ' + location.lng());*/
          document.getElementById("latlong").value=e;
        });
        }
        google.maps.event.addDomListener(window, 'load', codeAddress);