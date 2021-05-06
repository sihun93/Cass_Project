/**
 * 포인트 상품 구매
 */
function pointBuy() {	
	var point = document.getElementById('point').value;
		var pboardPrice = document.getElementById('pboardPrice').value;
		point = parseInt(point);
		pboardPrice = parseInt(pboardPrice);
		if (point < pboardPrice) {
			console.log(point + " , " + pboardPrice)
			alert('포인트가 부족합니다.');
			return;
		} else if (point >= pboardPrice) {
			if (confirm("구매하시면 포인트가 절감이 됩니다.\n 구매하시겠습니까?") == true) {
				var pointBuy = document.getElementById('pointBuy');		
				pointBuy.submit();
			} else {
				return;
			}
		}
	}