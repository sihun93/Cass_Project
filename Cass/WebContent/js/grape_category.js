/**
 * 구글 API 자바스크립트 성별 그래프
 */
google.charts.load('current', {'packages':['corechart']}); 
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string','Foods');
    data.addColumn('number','비중');
    data.addRows([ 
        ['키테고리1',5],
        ['카테고리2',3],
        ['카테고리3',3],
        ['카테고리4',3]
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
 
