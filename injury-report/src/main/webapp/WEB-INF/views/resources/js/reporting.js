/**
 * reporting.js
 */

//*******Variables********
var filtersLoaded = [
	{
		field : "bodypart",
		complete : false
	},
	{
		field : "employeePosition",
		complete : false
	},
	{
		field : "injuryType",
		complete : false
	},
	{
		field : "employee",
		complete : false
	},
	{
		field : "weather",
		complete : false
	}	
]

var dataset = [];
var w = 350;
var h = 350;
var padding = 10;

var sub_margin = {left : 30, right : 20, top : 20, bottom : 65};
var sub_dataset_bodypart = [];
var sub_dataset_injurytype = [];
var sub_w = 350 - sub_margin.left - sub_margin.right;
var sub_h = 200 - sub_margin.top - sub_margin.bottom;
var sub_padding = 2;

var injuryData = [
	results = {
		"employees" : [],
		"total" : 0,
		"selection" : "results"
	},
	others = {
		"employees" : [],
		"total" : 0,
		"selection" : "others"
	}
];

//*********Init**********
$(document).ready(function() {
	//Hide subcharts element
	$('#subcharts').hide();
	
	//Set properties on datepicker
	$('#report-date').datepicker({
        showOn: 'focus',
        showButtonPanel: true,
        closeText: 'Clear', // Text to show for "close" button
        todayText : ''
	});
	
	//when user clicks clear button on the datepicker, clear the
	//date value & redraw chart
	$('body').on('click', '.ui-datepicker-close', function(){
		$('#report-date').val('');
		drawChart();
	});
	
	//Prevent user from typing in a date
	$('#report-date').keydown(function(e) {
		   e.preventDefault();
		   return false;
		});
	
	//Set on change event for the chart type buttons
	$('input[type="radio"]').on('change', drawChart);
	
	//Generate filters
	generatePositionFilters();
	generateBodyPartFilters();
	generateInjuryTypeFilters();
	generateEmployeeFilters();
	generateWeatherFilters();
});

//Check to see if all filters are loaded
function checkFiltersLoaded(){
	var loaded = true;
	for(var i = 0; i < filtersLoaded.length; i++){
		if(filtersLoaded[i].complete == false){
			loaded = false;
		}
	}
	return loaded;
}

//***************************************
//**********Populate Filters*************
//***************************************
function generateBodyPartFilters(){
	var bodypartHtml = '';
	$.ajax({
		url : "/admintools/body",
		type : "GET"
	}).done(function(data) {
		bodypartHtml += '<option value="all">All</option>';
		for(var i = 0; i < data.length; i++){
			bodypartHtml += '<option value="' + data[i].bodyPartId + '">' + data[i].bodyPartName + '</option>';
		}
		$('#body-part').append(bodypartHtml);
		
		//Set value of 'complete' field inside filtersLoaded object with field of 'bodypart' to true
		//If all filters have been loaded, call fetch data			
		filtersLoaded.filter(function(d){if(d.field == "bodypart"){return d;}})[0].complete = true;
		if(checkFiltersLoaded()){fetchData();}
	});
}

function generatePositionFilters(){
	var positionHtml = '';
	$.ajax({
		url : "/admintools/position",
		type : "GET"
	}).done(function(data) {
		positionHtml += '<option value="all">All</option>';
		for(var i = 0; i < data.length; i++){
			positionHtml += '<option value="' + data[i].positionId + '">' + data[i].positionName + '</option>';
		}
		$('#employee-position').append(positionHtml);
		
		//Set value of 'complete' field inside filtersLoaded object with field of 'employeePosition' to true
		//If all filters have been loaded, call fetch data		
		filtersLoaded.filter(function(d){if(d.field == "employeePosition"){return d;}})[0].complete = true;
		if(checkFiltersLoaded()){fetchData();}
	});
}

function generateInjuryTypeFilters(){
	var injurytypeHtml = '';
	$.ajax({
		url : "/admintools/injury",
		type : "GET"
	}).done(function(data) {
		injurytypeHtml += '<option value="all">All</option>';
		for(var i = 0; i < data.length; i++){
			injurytypeHtml += '<option value="' + data[i].typeId + '">' + data[i].typeName + '</option>';
		}
		$('#injury-type').append(injurytypeHtml);
		
		//Set value of 'complete' field inside filtersLoaded object with field of 'injuryType' to true
		//If all filters have been loaded, call fetch data	
		filtersLoaded.filter(function(d){if(d.field == "injuryType"){return d;}})[0].complete = true;
		if(checkFiltersLoaded()){fetchData();}
	});
}

function generateEmployeeFilters(){
	var empHtml = '';
	$.ajax({
		url : "/reporting/allemployees",
		type : "GET"
	}).done(function(data) {
		empHtml += '<option value="all">All</option>';
		for(var i = 0; i < data.length; i++){
			empHtml += '<option value="' + data[i].employeeId + '">' + data[i].lastName + ', ' + data[i].firstName + '</option>';
		}
		$('#employee').append(empHtml);
		
		//Set value of 'complete' field inside filtersLoaded object with field of 'employee' to true
		//If all filters have been loaded, call fetch data			
		filtersLoaded.filter(function(d){if(d.field == "employee"){return d;}})[0].complete = true;
		if(checkFiltersLoaded()){fetchData();}
	});
}

function generateWeatherFilters(){
	var weatherHtml = '';
	$.ajax({
		url : "/admintools/weather",
		type : "GET"
	}).done(function(data) {
		weatherHtml += '<option value="all">All</option>';
		for(var i = 0; i < data.length; i++){
			weatherHtml += '<option value="' + data[i].weatherId + '">' + data[i].weatherCondition + '</option>';
		}
		$('#weather-type').append(weatherHtml);
		
		//Set value of 'complete' field inside filtersLoaded object with field of 'weather' to true
		//If all filters have been loaded, call fetch data			
		filtersLoaded.filter(function(d){if(d.field == "weather"){return d;}})[0].complete = true;
		if(checkFiltersLoaded()){fetchData();}
	});
}

//*************************
//*****Get all reports*****
//*************************
function fetchData(){
	$.ajax({
		url : "/reporting/allreports",
		type : "GET"
	}).done(function(data) {
		for(var i = 0; i < data.length; i++){
			dataset.push({
				"employee" : data[i].employee,
				"bodypart" : data[i].bodyPart,
				"weather" : data[i].weather,
				"injurydate" : '2014-11-20',
				"datereported" : data[i].dateReportedToManagement,
				"injurytype" : data[i].injuryType,
				"enabled" : data[i].enabled
			});
		}
		drawChart();
	});	
}

//****************************
//*******Utilities************
//****************************
function resetInjuryData(){
	injuryData[0].total = 0;
	injuryData[0].employees = [];
	injuryData[1].total = 0;
	injuryData[1].employees = [];
}

//Toggle active chart class & draw report if that report is
//not already active
function toggleChartType(type){
	if(type=='bar'){
		if(!$('#chart-type-list #bar-graph').hasClass('active')){
			$('#chart-type-list #bar-graph').addClass('active');
			$('#chart-type-list #pie-chart').removeClass('active');
			drawChart();
		}	
	}
	else if(type=='pie'){
		if(!$('#chart-type-list #pie-chart').hasClass('active')){
			$('#chart-type-list #pie-chart').addClass('active');
			$('#chart-type-list #bar-graph').removeClass('active');
			drawChart();
		}		
	}
}

//Given two dates in date1=YYYY-mm-dd, date2=YYYY/mm/dd
//format, return if they're equal
function compareDates(date1, date2){
	var date1_parts = date1.split('-');
	var date1_object = new Date(date1_parts[0], date1_parts[1]-1, date1_parts[2]);
	var date2_parts = date2.split('/');
	var date2_object = new Date(date2_parts[2], date2_parts[0]-1, date2_parts[1]);
	return date1_object.getTime() == date2_object.getTime();
}

//When only the info message is showing in the subChart panel,
//remove all margins & vice versa, cuz I thought looked cooler.
//There's a better way to invoke this toggle, but I got lazy.
function subchartPanelAddMargin(){
	$('#subchart-panel-body').css({"margin-bottom": "0px"});
}

function subchartPanelRemoveMargin(){
	$('#subchart-panel-body').css({"margin-bottom": "-20px"});
}

//*******************************
//********Draw Functions*********
//*******************************
function drawChart(){
	if($('#chart-type-list #bar-graph').hasClass('active')){
		drawBarChart();
	}
	else if($('#chart-type-list #pie-chart').hasClass('active')){
		drawPieChart();
	}
}

function updateChart(){
	if($('#chart-type-list #bar-graph').hasClass('active')){
		updateBarChart();
	}
	else if($('#chart-type-list #pie-chart').hasClass('active')){
		updatePieChart();
	}	
}

function drawBarChart(){
	$('#chart').empty();
	filter();
	var totalResults = d3.sum(injuryData, function(d){ return d.employees.length; });
	var canvas = d3.select("#chart")
		.append("svg")
		.attr({
			width : w,
			height : h
		});
	
	//Set height scale domain from 0 to max element val
	//Set range from 0 to height
	var heightScale = d3.scale.linear()
		.domain([0, d3.max(injuryData, function(d){ return d.total; })])
		.range([0, h]);
	
	//Tooltip to be displayed when hovering over report
	//tooltip class is in .css file & positions the tooltip
	//by the cursor position
	var div = d3.select("body").append("div")   
	    .attr("class", "tooltip")               
	    .style("opacity", 0);	
	
	//Add rectangles to svg
	//On mouseover show tooltip & call drawSubCharts Method
	//On mouseout, hide div & subcharts
	var chart = canvas.selectAll("rect")
		.data(injuryData)
		.enter()
			.append("rect")
				.attr({
					width : w/injuryData.length - padding,
					height : 0,
					x : function(d, i){ return i * (w/injuryData.length);},
					y : h,
					fill : function(d){ return d.selection == "results" ? "#428bca" : "gray"; }
				})
			    .on("mouseover", function(d) {
			    	$('#subchart-message').hide();
			    	drawSubCharts(d.selection);
				    div.transition()        
				        .duration(200)      
				        .style("opacity", .9);
				    var divHtml = '<div class="alert alert-info"><span class="glyphicon glyphicon-info-sign"></span> ' +  d.employees.length + ' <span class="total-results">total results</span></div>';
				    div.html(divHtml) 
				        .style("left", (d3.event.pageX) + "px")     
				        .style("top", (d3.event.pageY)-50 + "px");    
				    })
				.on("mouseout", function(d) {
					subchartPanelRemoveMargin()
					$('#subchart-message').show();
					hideSubChart();
				    div.transition()        
				        .duration(500)      
				        .style("opacity", 0);   
				});
	
	//Add pct label to each bar chart element
	//specifying the pct of results. If no results
	//in either display No Results
	canvas.selectAll("text")
		.data(injuryData)
		.enter()
			.append("text")
				.text(function(d, i){
					//If there are results, display pct
					if(totalResults > 0){
						var pct = Number(d.employees.length/totalResults);
						return pct > 0 ? (pct*100).toFixed(1) + "%" : "0.0%";
					}
					else if(i == 0){
						return "No results";
					}
				})
				//Special handling in case of no results, the 'No Results'
				//mesage should be positioned in center of svg, fill black
				//and modify font size
				.attr({
					x : function(d, i){
						if(totalResults > 0){ return i * (w/injuryData.length) + w/injuryData.length/2; }
						else if(i==0){ return w/2; }
					},
					y : function(d, i){
						if(totalResults > 0){ return h - heightScale(d.total)/2 + 15; }
						else if(i==0){ return h/2+18; }							
					},				
					fill : function(d, i){
						if(totalResults > 0){ return "white"; }
						else { return "black"; }							
					},
					"font-size": function(d, i){
						if(totalResults > 0){ return "30px"; }
						else if(i==0){ return "46px"; }						
					},
					"text-anchor" : "middle"
				});
	
	//Animate chart from bottom to top, left to right
	chart.transition()
		.duration(1000)
		.delay(function(d, i){ return i*20; })
			.attr({
				height : function(d){ return heightScale(d.total); },
				y : function(d){ return h - heightScale(d.total);}
			})
			.ease('elastic', 1, .85);
}

function drawPieChart(){	
	$('#chart').empty();
	filter();	
	var radius = Math.min(w, h) / 2;
	
	var div = d3.select("body").append("div")   
	    .attr("class", "tooltip")               
	    .style("opacity", 0);	
	
	var pie = d3.layout.pie()
		.value(function(d){
			return d.total;
		});
	
	//Create svg.arc, set inner radius = radius/1.5
	//to create 'donut' look
	var arc = d3.svg.arc()
		.outerRadius(radius)
		.innerRadius(radius/1.5);
	
	//append group element inside '#chart svg' in which the
	//pie chart will be placed
	var myChart = d3.select('#chart').append("svg")
		.attr("width", w)
		.attr("height", h)
		.append('g')
			.attr("transform", 'translate('+(w-radius)+','+(h-radius)+')')
			.selectAll('path').data(pie(injuryData))
			.enter().append('path')
				.attr('fill', function(d,i){
					return d.data.selection == "results" ? "#428bca" : "gray";
				})
				.attr('d', arc)
		//Same mouseover & mouseout event handlers as bar chart
	    .on("mouseover", function(d) {	    
	    	$('#subchart-message').hide();
	    	drawSubCharts(d.data.selection);
		    div.transition()        
		        .duration(200)      
		        .style("opacity", .9);
		    var divHtml = '<div class="alert alert-info"><span class="glyphicon glyphicon-info-sign"></span> ' +  d.data.employees.length + ' <span class="total-results">total results</span></div>';
		    div.html(divHtml) 
		        .style("left", (d3.event.pageX) + "px")     
		        .style("top", (d3.event.pageY)-50 + "px");    
		    })
		.on("mouseout", function(d) { 
			subchartPanelRemoveMargin()
			$('#subchart-message').show();
			hideSubChart();
		    div.transition()        
		        .duration(500)      
		        .style("opacity", 0);   
		})
		//animate each piece of pie chart to start
		//from 0deg and grow to end angle
		.transition().duration(200)
			.attrTween('d', function(d) {
			   var i = d3.interpolate(d.startAngle+0.1, d.endAngle);
			   return function(t) {
			       d.endAngle = i(t);
			     return arc(d);
			   };
			});
	
	//Append text inside pie chart
	//specifying pct of results are
	//approved injury reports
	d3.select("#chart svg")
		.selectAll("text")
		.data(injuryData)
		.enter()
			.append("text")
			.attr("x", radius)
			.attr("y", radius + 18)
			.attr("fill", "black")
			.attr("font-size", "46px")
			.attr("text-anchor", "middle")
			.text(function(d, i){
				if(d3.sum(injuryData, function(d){ return d.employees.length; }) > 0){
					if(i == 0){
						var pct = Number(d.employees.length/(d3.sum(injuryData, function(d){ return d.employees.length; })));
						return pct > 0 ? (pct*100).toFixed(1) + "%" : "0.0%";
					}
				}
				else{
					return "No results";
				}
			});
}

//See drawBarChart for comments
function updateBarChart(){
	filter();
	var totalResults = d3.sum(injuryData, function(d){ return d.employees.length; });
	var canvas = d3.select("#chart");
	var heightScale = d3.scale.linear()
		.domain([0, d3.max(injuryData, function(d){ return d.total; })])
		.range([0, h]);
	
	var div = d3.select("body").append("div")   
	    .attr("class", "tooltip")               
	    .style("opacity", 0);	
	
	var chart = canvas.selectAll("rect")
		.data(injuryData);
		chart.exit().remove();
		chart.enter()
			.append("rect")
				.attr({
					width : w/injuryData.length - padding,
					height : 0,
					x : function(d, i){ return i * (w/injuryData.length);},
					y : h,
					fill : function(d){ return d.selection == "results" ? "#428bca" : "gray"; }
				});
		
	chart.transition()
		.duration(1000)
		.delay(function(d, i){ return i*20; })
			.attr({
				height : function(d){ return heightScale(d.total); },
				y : function(d){ return h - heightScale(d.total);}
			})
			.ease('elastic');
	
	canvas.selectAll("text").remove();
	var svg = canvas.select("svg");
	svg.selectAll("text")
		.data(injuryData)
		.enter()
		.append("text")
			.text(function(d, i){
				if(totalResults > 0){
					var pct = Number(d.employees.length/totalResults);
					return pct > 0 ? (pct*100).toFixed(1) + "%" : "0.0%";
				}
				else if(i == 0){
					return "No results";
				}
			})
			.attr({
				x : function(d, i){
					if(totalResults > 0){ return i * (w/injuryData.length) + w/injuryData.length/2; }
					else if(i==0){ return w/2; }
				},
				y : function(d, i){
					if(totalResults > 0){ return h - heightScale(d.total)/2 + 15; }
					else if(i==0){ return h/2+18; }							
				},				
				fill : function(d, i){
					if(totalResults > 0){ return "white"; }
					else { return "black"; }							
				},
				"font-size": function(d, i){
					if(totalResults > 0){ return "30px"; }
					else if(i==0){ return "46px"; }						
				},
				"text-anchor" : "middle"
			});
}

function updatePieChart(){
	drawPieChart();
}

//*************************
//******Filter data********
//*************************
function filter(){
	resetInjuryData();
	var inFilter = false;
	var filteredDataset = [];
	
	for (var i = 0; i < dataset.length; i++) {
		inFilter = true;
		if($('#body-part').val()!='all' && inFilter){
			inFilter = (dataset[i].bodypart.bodyPartId == $('#body-part').val()); 
		}
		if($('#employee-position').val()!='all' && inFilter){
			inFilter = (dataset[i].employee.position.positionId == $('#employee-position').val());
		}
		if($('#injury-type').val()!='all' && inFilter){
			inFilter = (dataset[i].injurytype.typeId == $('#injury-type').val());
		}
		if($('#employee').val()!='all' && inFilter){
			inFilter = (dataset[i].employee.employeeId == $('#employee').val());
		}
		if($('#weather-type').val()!='all' && inFilter){
			inFilter = (dataset[i].weather.weatherId == $('#weather-type').val());
		}
		if($('#report-date').val().length > 0 && inFilter){
			inFilter = compareDates(dataset[i].datereported, $('#report-date').val());
		}		
		
		if(inFilter){
			filteredDataset.push(dataset[i]);
		}
	}		
	
	for (var i = 0; i < filteredDataset.length; i++) {
		inFilter = filteredDataset[i].enabled;
		if($('#body-part').val()!='all' && inFilter){
			inFilter = (filteredDataset[i].bodypart.bodyPartId == $('#body-part').val()); 
		}
		if($('#employee-position').val()!='all' && inFilter){
			inFilter = (filteredDataset[i].employee.position.positionId == $('#employee-position').val());
		}
		if($('#injury-type').val()!='all' && inFilter){
			inFilter = (filteredDataset[i].injurytype.typeId == $('#injury-type').val());
		}
		if($('#employee').val()!='all' && inFilter){
			inFilter = (filteredDataset[i].employee.employeeId == $('#employee').val());
		}
		if($('#weather-type').val()!='all' && inFilter){
			inFilter = (filteredDataset[i].weather.weatherId == $('#weather-type').val());
		}
		if($('#report-date').val().length > 0 && inFilter){
			inFilter = compareDates(filteredDataset[i].datereported, $('#report-date').val());
		}		
		
		if(inFilter){
			injuryData[0].total += 1;
			injuryData[0].employees.push(filteredDataset[i]);
		}
		else{
			injuryData[1].total += 1;
			injuryData[1].employees.push(filteredDataset[i]);
		}
	}	
}




//**********************************
//************Sub Charts************
//**********************************

/**
 * Generate a dataset of all injury reports
 * grouped by BODYPART withing the currently
 * selected filter.
 * type_index specifies which element in the
 * injuryData object to use (either 'results=0' or 'others=1') 
 */
function generateBodyPartSubChartData(type_index){
	sub_dataset_bodypart = [];
	var sum = 0;
	//Loop through each element in the bodypart list (skipping
	//the first 'All' element)
	for(var i = 1; i < $('#body-part option').length; i++){
		//Set sum equal to the total number of reports matching
		//the current body part
		sum = d3.sum(injuryData[type_index].employees, function(d){
			if(d.bodypart.bodyPartId == $('#body-part option')[i].value){
				return 1;
			}
		});
		//If sum is not 0, add an item to the sub_dataset_bodypart
		//array list with the count & body-part type
		if(sum != 0){
			sub_dataset_bodypart.push({
				"type" : $('#body-part option')[i].innerHTML,
				"count" : sum
			});
		}
	}
}

/**
 * Same as generateBodyPartSubChartData
 * @param type_index
 */
function generateInjuryTypeSubChartData(type_index){
	sub_dataset_injurytype = [];
	var sum = 0;
	for(var i = 1; i < $('#injury-type option').length; i++){
		sum = d3.sum(injuryData[type_index].employees, function(d){
			if(d.injurytype.typeId == $('#injury-type option')[i].value){
				return 1;
			}
		});
		if(sum != 0){
			sub_dataset_injurytype.push({
				"type" : $('#injury-type option')[i].innerHTML,
				"count" : sum				
			});
		}
	}
}

/**
 * Draw subchart based on the selected type
 * (either 'results=0' or 'others=1')
 * @param type
 */
function drawSubCharts(type){
	subchartPanelAddMargin();
	$('#subcharts').fadeIn(100);
	var type_index = type == "results" ? 0 : 1;
	drawBodyPartSubChart(type_index);
	drawInjuryTypeSubChart(type_index);
}

/**
 * Draw the BodyPartSubChart
 * @param type_index
 */
function drawBodyPartSubChart(type_index){
	$('#sub-chart-bodypart').empty();
	generateBodyPartSubChartData(type_index);
	var canvas = d3.select("#sub-chart-bodypart")
		.append("svg")
		.attr({
			width : sub_w + sub_margin.left + sub_margin.right,
			height : sub_h + sub_margin.top + sub_margin.bottom
		});
	
	//set domain from 0 to largest & range from 0 to height of svg
	var heightScale = d3.scale.linear()
		.domain([0, d3.max(sub_dataset_bodypart, function(d){ return d.count; })])
		.range([0, sub_h]);
	
	//Set color scale domain from smallest element to largest element,
	//and range from yellow to orange
	var colorScale = d3.scale.linear()
		.domain([d3.min(sub_dataset_bodypart, function(d){ return d.count; }), d3.max(sub_dataset_bodypart, function(d){ return d.count; })])
		.range(["#FFEE00", "#FF9500"]);	
	
	//Add rectangle elements.
	//Height is set to zero & y-pos to svg height, because chart will animate from bottom.
	//Width is set based on num elements & width of svg offset by padding.
	//Transform property used to move chart inside margins 
	var chart = canvas.selectAll("rect")
		.data(sub_dataset_bodypart)
		.enter()
			.append("rect")
				.attr({
					width : function(d, i){return sub_w / sub_dataset_bodypart.length - sub_padding;},
					height : 0,
					x : function(d, i){ return i * (sub_w/sub_dataset_bodypart.length);},
					y : sub_h,
					fill : function(d){return colorScale(d.count);},
					transform : "translate(" + sub_margin.left + ", " + sub_margin.top + ")"
				});	
	
	//Animate from bottom to top, left to right
	chart.transition()
		.duration(300)
		.delay(function(d, i){ return i*20; })
			.attr({
				height : function(d){ return heightScale(d.count); },
				y : function(d, i){ return sub_h - heightScale(d.count);}
			})
			.ease('elastic', 1, .85);
	
	//Y-Axis
	//Set range from h to 0, so the y-axis labels
	//display in ascending order
	vGuideScale = d3.scale.linear()
		.domain([0, d3.max(sub_dataset_bodypart, function(d){ return d.count; })])
		.range([sub_h, 0]);
	
	var vAxis = d3.svg.axis()
		.scale(vGuideScale)
		.orient('left')
		.ticks(2);
	
	//Create y-axis
	var vGuide = d3.select('#sub-chart-bodypart svg').append('g');
		vAxis(vGuide);
		vGuide.attr('transform', 'translate(' + sub_margin.left + ',' + sub_margin.top +  ')');
		vGuide.selectAll('path')
			.style({fill:'none', stroke: '#000'});
		vGuide.selectAll('line')
			.style({stroke:'#000'});
		
	
	//X-axis
	var x = d3.scale.ordinal()
		.rangeRoundBands([0, sub_w], .1);	
	
	//Declare x-axis object (used when appending actual svg element)
	//tickSize() outerTick sizes are set to 0, so the first and last tick
	//elements are not shown
	var xAxis = d3.svg.axis()
		.scale(x)
		.tickSize(8,0)
		.orient("bottom");		
	
	//Set x domain to eachc element in sub_dataset_bodypart.
	//These will be the x-axis labels
	x.domain(sub_dataset_bodypart.map(function(d) { return d.type; }))
	
	//Add the x-axis element
	var xAxisGroup = d3.select('#sub-chart-bodypart svg')
		.append("g").call(xAxis)
			.attr("class", "x axis")
			.attr("transform", "translate("+sub_margin.left + "," + (sub_h + sub_margin.top) + ")")
			.style({fill:'none', stroke: '#000', "font-size" : "12px"})
			.call(xAxis);
	
	//Rotate the text 45deg, and adjust x & y vals for display purposes
	d3.select("#sub-chart-bodypart svg g.x.axis")
		.selectAll("text")
			.attr("transform", function (d) {return "translate(-17, 15) rotate(-45)";});        
}

/**
 * See drawBodyPartSubChart for comments
 * @param type_index
 */
function drawInjuryTypeSubChart(type_index){
	$('#sub-chart-injurytype').empty();
	generateInjuryTypeSubChartData(type_index);
	var canvas = d3.select("#sub-chart-injurytype")
		.append("svg")
		.attr({
			width : sub_w + sub_margin.left + sub_margin.right,
			height : sub_h + sub_margin.top + sub_margin.bottom
		});
	
	var heightScale = d3.scale.linear()
		.domain([0, d3.max(sub_dataset_injurytype, function(d){ return d.count; })])
		.range([0, sub_h]);
	
	var colorScale = d3.scale.linear()
		.domain([d3.min(sub_dataset_injurytype, function(d){ return d.count; }), d3.max(sub_dataset_injurytype, function(d){ return d.count; })])
		.range(["#FFCFCF", "#F02E2E"]);	
	
	var chart = canvas.selectAll("rect")
		.data(sub_dataset_injurytype)
		.enter()
			.append("rect")
				.attr({
					width : function(d, i){return sub_w / sub_dataset_injurytype.length - sub_padding;},
					height : 0,
					x : function(d, i){ return i * (sub_w/sub_dataset_injurytype.length);},
					y : sub_h,
					fill : function(d){ return colorScale(d.count); },
					transform : "translate(" + sub_margin.left + ", " + sub_margin.top + ")"
				});	
	
	chart.transition()
		.duration(300)
		.delay(function(d, i){ return i*20; })
			.attr({
				height : function(d){ return heightScale(d.count); },
				y : function(d){ return sub_h - heightScale(d.count);}
			})
			.ease('elastic', 1, .85);
	
	vGuideScale = d3.scale.linear()
		.domain([0, d3.max(sub_dataset_injurytype, function(d){ return d.count; })])
		.range([sub_h, 0]);

	var vAxis = d3.svg.axis()
		.scale(vGuideScale)
		.orient('left')
		.ticks(2);
	
	var hAxis = d3.svg.axis()
		.scale(vGuideScale)
		.orient('left')
		.ticks(2);
	
	var vGuide = d3.select('#sub-chart-injurytype svg').append('g');
		vAxis(vGuide);
		vGuide.attr('transform', 'translate(' + sub_margin.left + ',' + sub_margin.top +  ')');
		vGuide.selectAll('path')
			.style({fill:'none', stroke: '#000'});
		vGuide.selectAll('line')
			.style({stroke:'#000'});
		
		
		var x = d3.scale.ordinal()
			.rangeRoundBands([0, sub_w], .1);	
		
		var xAxis = d3.svg.axis()
			.scale(x)
			.tickSize(10,0)
			.orient("bottom");		

      x.domain(sub_dataset_injurytype.map(function(d) { return d.type; }))

		var xAxisGroup = d3.select('#sub-chart-injurytype svg')
			.append("g").call(xAxis)
				.attr("class", "x axis")
				.attr("transform", "translate("+sub_margin.left + "," + (sub_h + sub_margin.top) + ")")
				.style({fill:'none', stroke: '#000', "font-size" : "12px"})
				.call(xAxis);	
      
    d3.select("#sub-chart-injurytype svg g.x.axis")
  	.selectAll("text")
	    .attr("transform", function (d) {return "translate(-20, 20) rotate(-45)";});
}

function hideSubChart(){
	$('#subcharts').hide(0,function(){
		$('#sub-chart-weather').empty();
		$('#sub-chart-injurytype').empty();
	});
}