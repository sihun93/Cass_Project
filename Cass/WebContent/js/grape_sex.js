/**
 * 구글 API 자바스크립트 성별 그래프
 */
google.charts.load('current', {'packages':['corechart']}); 
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var mCount = parseInt(document.getElementById('mCount').value);
    var fCount = parseInt(document.getElementById('fCount').value);
    var data = new google.visualization.DataTable();
    data.addColumn('string','Foods');
    data.addColumn('number','비중');
    data.addRows([ 
        ['여자 ' + fCount + '명',fCount],
        ['남자 ' + mCount + '명',mCount]
    ]);
    var opt = {
            'title':'사용자 성별',
            'width':300,
            'height':300,
            pieSliceText:'label',
            legend:'none', 
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('sexPiChart'));
    chart.draw(data,opt);
}
 
