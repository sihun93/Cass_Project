<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>Companion Animal Service Site</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=9w8kacxh1u&submodules=geocoder"></script>
</head>
<body>
	<input type="hidden" id="addrdata" value="aa">
	<div id="map" style="width: 600px; height: 600px;"></div>
	<input type="hidden" id="pointx">
	<input type="hidden" id="pointy">
	<script>
	$("#addrdata").val(window.opener.$("#mapaddr").val());
			
			var mapOptions = {
				center : new naver.maps.LatLng(37, 127),zoom : 17
			};
			
			var map = new naver.maps.Map('map', mapOptions);
			var infoWindow = new naver.maps.InfoWindow({
			    anchorSkew: true
			});
			map.setCursor('pointer');
			function searchAddressToCoordinate(address) {
			    naver.maps.Service.geocode({
			        query: address
			    }, function(status, response) {
			        if (status === naver.maps.Service.Status.ERROR) {
			            return alert('Something Wrong!');
			        }

			        if (response.v2.meta.totalCount === 0) {
			            return alert('totalCount' + response.v2.meta.totalCount);
			        }

			        var htmlAddresses = [],
			            item = response.v2.addresses[0],
			            point = new naver.maps.Point(item.x, item.y);

			        if (item.roadAddress) {
			            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
			        }

			        if (item.jibunAddress) {
			            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
			        }

			        if (item.englishAddress) {
			            htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
			        }
			        infoWindow.setContent([
			            '<div style="padding:10px;min-width:50px;line-height:150%;">',
			            '<h6 style="margin-top:5px;">검색 주소 : '+ address +'</h6><br />',
			            htmlAddresses.join('<br />'),
			            '</div>'
			        ].join('\n'));

			        map.setCenter(point);
			        infoWindow.open(map, point);
			    });
			}
			addr =$("#addrdata").val();
			searchAddressToCoordinate(addr);

	</script>
</body>
</html>