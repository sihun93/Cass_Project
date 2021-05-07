/**
 * 구글 API 자바스크립트 select 파이 차트
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
        ['애완용품',selectCount1],
        ['관리',selectCount2],
        ['교육',selectCount3],
        ['사료',selectCount4]
    ]);
    var opt = {
            'title':'이용자별 인기카테고리',
            'width':800,
            'height':800,
            is3D:'true',
            pieSliceText:'label',
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('selectPiChart'));
    chart.draw(data,opt);
}
 
