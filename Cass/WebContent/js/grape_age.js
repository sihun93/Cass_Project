/**
 * 구글 API 자바스크립트 성별 그래프
 */
google.charts.load('current', {'packages':['corechart']}); 
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = new google.visualization.DataTable();
    var Count10 = parseInt(document.getElementById('10Count').value);
    var Count20 = parseInt(document.getElementById('20Count').value);
    var Count30 = parseInt(document.getElementById('30Count').value);
    var Count40 = parseInt(document.getElementById('40Count').value);
    var Count50 = parseInt(document.getElementById('50Count').value);
    data.addColumn('string','Foods');
    data.addColumn('number','비중');
    data.addRows([ 
        ['10대' + Count10 + '명',Count10],
        ['20대' + Count20 + '명',Count20],
        ['30대' + Count30 + '명',Count30],
        ['40대' + Count40 + '명',Count40],
        ['50대' + Count50 + '명',Count50]
    ]);
    var opt = {
            'title':'이용자 나이',
            'width':500,
            'height':500,
            is3D:'true',
            pieSliceText:'label',
			backgroundColor: '#F4F3EF'
    };
    var chart = new google.visualization.PieChart(document.getElementById('agePiChart'));
    chart.draw(data,opt);
}
 
