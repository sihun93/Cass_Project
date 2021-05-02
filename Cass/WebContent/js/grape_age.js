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
        ['20대',5],
        ['30대',3],
        ['40대',3],
        ['50대',3]
    ]);
    var opt = {
            'title':'사용자 나이',
            'width':300,
            'height':300,
            pieSliceText:'label',
            legend:'none', 
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('agePiChart'));
    chart.draw(data,opt);
}
 
