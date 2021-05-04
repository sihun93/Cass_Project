/**
 * 구글 API 자바스크립트 성별 그래프
 */
google.charts.load('current', {'packages':['corechart']}); 
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = new google.visualization.DataTable();
    var cateCount1 = parseInt(document.getElementById('cateCount1').value);
    var cateCount2 = parseInt(document.getElementById('cateCount2').value);
    var cateCount3 = parseInt(document.getElementById('cateCount3').value);
    var cateCount4 = parseInt(document.getElementById('cateCount4').value);
    data.addColumn('string','Foods');
    data.addColumn('number','비중');
    data.addRows([ 
        ['키테고리1',cateCount1],
        ['카테고리2',cateCount2],
        ['카테고리3',cateCount3],
        ['카테고리4',cateCount4]
    ]);
    var opt = {
            'title':'인기카테고리',
            'width':300,
            'height':300,
            pieSliceText:'label',
            legend:'none', 
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('categoryPiChart'));
    chart.draw(data,opt);
}
 
