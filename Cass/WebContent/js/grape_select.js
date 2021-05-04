/**
 * 구글 API 자바스크립트 성별 그래프
 */
google.charts.load('current', {'packages':['corechart']}); 
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = new google.visualization.DataTable();
    var selectCount1 = parseInt(document.getElementById('selectCount1').value);
    var selectCount2 = parseInt(document.getElementById('selectCount2').value);
    var selectCount3 = parseInt(document.getElementById('selectCount3').value);
    var selectCount4 = parseInt(document.getElementById('selectCount4').value);
    data.addColumn('string','Foods');
    data.addColumn('number','비중');
    data.addRows([ 
        ['카테고리1',selectCount1],
        ['카테고리2',selectCount2],
        ['카테고리3',selectCount3],
        ['카테고리4',selectCount4]
    ]);
    var opt = {
            'title':'이용자별 인기카테고리',
            'width':500,
            'height':500,
            pieSliceText:'label',
            legend:'none', 
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('selectPiChart'));
    chart.draw(data,opt);
}
 
