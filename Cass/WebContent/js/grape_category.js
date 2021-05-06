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
        ['애완용품',cateCount1],
        ['관리',cateCount2],
        ['교육',cateCount3],
        ['사료',cateCount4]
    ]);
    var opt = {
            'title':'인기카테고리',
            'width':500,
            'height':500,
            is3D:'true',
            pieSliceText:'label',
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('categoryPiChart'));
    chart.draw(data,opt);
}
 
