(function($) {
	if (typeof SMILE == 'undefined') {
		SMILE = new Object();
	}
	
	SMILE.getRandom=function(m){
		if(m){
			return ((Math.round(Math.random()*100)))%m+1;
		}else{
			return ((Math.round(Math.random()*100)))%10+1;
		}
	}
	
	SMILE.generateRow = function (id,ctx1,ctx2,ctx3){
		time=SMILE.getFormatTime(ctx1);
        var rowDiv=$("<div />");
        /*switch(SMILE.getRandom(3)){
        case 4:$(rowDiv).addClass("ss-row ");break;
        case 1:$(rowDiv).addClass("ss-row ss-small sm-expt");break;
        case 2:$(rowDiv).addClass("ss-row ss-medium sm-expt");break;
        case 3:$(rowDiv).addClass("ss-row ss-large sm-expt");break;
        }*/
        $(rowDiv).addClass("ss-row ss-large sm-expt");
        var leftDiv=$("<div/>").addClass("ss-left");
        var rightDiv=$("<div/>").addClass("ss-right");
        //data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?"
        var contentA=$("<a href='javascript:return false;'></a>");
        $(contentA).attr({
        	'data-toogle':'popover',
        	'data-container':'body',
        	'data-content':ctx3
        });
        if($("#ss-container > div.sm-expt").size()%2==1){
        	$(contentA).attr('data-placement','left');
        	$(leftDiv).append($("<a href='javascript:return false;'>hello</a>").addClass("ss-circle ").css("background-image","url(/content/img/"+id+"/)"));
        	$(rightDiv).attr("id",id).append($("<h3 />").append($("<span />").text(time)).append($(contentA).text(ctx2).attr("title",ctx1)));
        	$(leftDiv).click(function(){
                $(this).next().trigger("click");
            });
        	$(rightDiv).click(function(){
        		var dataTitle=$("a",this).text();
        		var dataContent=$("a",this).attr("data-content");
        		var dataTime=$("a",this).attr("data-original-title");
        		var dataId=$(this).attr("id");
        		$("#content-title",$("#myModal")).val(dataTitle);
        		$("#content-content",$("#myModal")).val(dataContent);
        		$("#content-time",$("#myModal")).val(dataTime);
        		$("#content-id",$("#myModal")).val(dataId);
                $(".fileinput-remove-button").trigger("click");
        		$("#myModal").modal('toggle');
        	});
        }else{
        	$(contentA).attr('data-placement','right');
        	$(leftDiv).attr("id",id).append($("<h3 />").append($("<span />").text(time)).append($(contentA).text(ctx2).attr("title",ctx1)));
            $(rightDiv).click(function(){
                $(this).prev().trigger("click");
            });
        	$(leftDiv).click(function(){
        		var dataTitle=$("a",this).text();
        		var dataContent=$("a",this).attr("data-content");
        		var dataTime=$("a",this).attr("data-original-title");
        		var dataId=$(this).attr("id");
        		$("#content-title",$("#myModal")).val(dataTitle);
        		$("#content-content",$("#myModal")).val(dataContent);
        		$("#content-time",$("#myModal")).val(dataTime);
        		$("#content-id",$("#myModal")).val(dataId);
                $(".fileinput-remove-button").trigger("click");
        		$("#myModal").modal('toggle');
        	});
        	$(rightDiv).append($("<a href='javascript:return false;'>hello</a>").addClass("ss-circle ").css("background-image","url(/content/img/"+id+"/)"));
        }
        $(contentA).popover({html : true });
        $(contentA).mouseout(function(){
        	$(contentA).popover('hide');
        });
        $(contentA).hover(function(){
        	$(contentA).popover('show');
        });
        $(rowDiv).append(leftDiv).append(rightDiv);
        return rowDiv;
	}
	
	
	SMILE.generateTimeRow = function (time){
		var year = time.substring(0,4);
		var month = time.substring(5,7);
		if($("#"+year+"-"+month).size()>0){
			return "";
		}
		var flag=true;
		var sslas=$("#ss-links > a");
		if(sslas.size()>0){
			for(var n =0;n< sslas.size();n++){
				var a=sslas[n];
				if($(a).text() == year){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			$("#ss-links").append($("<a/>").attr("href","#"+year+"-"+month).text(year));
		}
		var rowDiv=$("<div />").addClass("ss-row sm-expt1 ").attr("id",year+"-"+month);
		month=SMILE.getEnMonth(month);
        if($("#ss-container > div.sm-expt1").size()%2==1){
        	var leftDiv=$("<div/>").addClass("ss-left").append($("<h2 />").text(month));
            var rightDiv=$("<div/>").addClass("ss-right").append($("<h2 />").text(year));
            $(rowDiv).append(leftDiv).append(rightDiv);
        }else{
        	var leftDiv=$("<div/>").addClass("ss-left").append($("<h2 />").text(year));
            var rightDiv=$("<div/>").addClass("ss-right").append($("<h2 />").text(month));
            $(rowDiv).append(leftDiv).append(rightDiv);
        }
        return rowDiv;
	}
	SMILE.getFormatTime=function(time){
		return SMILE.getEnMonth(time.substring(5,7))+" "+time.substring(8,10)+" ,"+time.substring(0,4);
	}
	SMILE.getEnMonth=function(month){
		switch(month){
		case "01" : month = "Janary";break;
		case "02" : month = "February";break;
		case "03" : month = "March";break;
		case "04" : month = "April";break;
		case "05" : month = "May";break;
		case "06" : month = "June";break;
		case "07" : month = "July";break;
		case "08" : month = "August";break;
		case "09" : month = "September";break;
		case "10" : month = "October";break;
		case "11" : month = "Novenber";break;
		case "12" : month = "December";break;
		}
		return month;
	}
	SMILE.showContent=function(url,page){
		$.ajax({
			"url":url,
			"method":"post",
			"data":{
				"page":page
			},
			"dataType":"json",
			"success":function(contents){
				if(contents.length<10){
					$("#loadMore").hide();
				}
				for(var n in contents){
					$("#ss-container").append(SMILE.generateTimeRow(contents[n].octime));
					$("#ss-container").append(SMILE.generateRow(contents[n].id,contents[n].octime,contents[n].title,contents[n].content));
				}
				SMILE.init($('#ss-container > div.ss-row'));
			}
		});
	}
	SMILE.showDays=function(day){
		day=parseInt(day);
		var daySpan=$("<span />").addClass("countDays");
		while(day>0.999){
			var pSpan=$("<span />").addClass("position");
			$(pSpan).append($("<span class='digit static' style='top: 0px; opacity: 1;'/>").text(day%10));
			day=Math.floor(day/10);
			daySpan.prepend(pSpan);
		}
		return daySpan;
	}
	SMILE.init = function(ss_rows) {
		var $sidescroll = (function() {
			var $rows = ss_rows,
			$rowsViewport, $rowsOutViewport,
			$links = $('#ss-links > a'),
			$win = $(window),
			winSize = {},
			anim = false,
			scollPageSpeed = 2000,
			scollPageEasing = 'easeInOutExpo',
			hasPerspective = false,
			perspective = hasPerspective && Modernizr.csstransforms3d,
			init = function() {
				getWinSize();
				initEvents();
				defineViewport();
				setViewportRows();
				if (perspective) {
					$rows.css({
						'-webkit-perspective' : 600,
						'-webkit-perspective-origin' : '50% 0%'
					});
				}
				$rowsViewport.find('a.ss-circle').addClass('ss-circle-deco');
				placeRows();

			},
			defineViewport = function() {
				$.extend($.expr[':'], {
					inviewport : function(el) {
						if ($(el).offset().top < winSize.height) {
							return true;
						}
						return false;
					}
				});
			},
			setViewportRows = function() {
				$rowsViewport = $rows.filter(':inviewport');
				$rowsOutViewport = $rows.not($rowsViewport)
			},
			getWinSize = function() {
				winSize.width = $win.width();
				winSize.height = $win.height();
			},
			initEvents = function() {
				$links.on('click.Scrolling', function(event) {
					$('html, body').stop().animate({
						scrollTop : $($(this).attr('href')).offset().top
					}, scollPageSpeed, scollPageEasing);
					return false;
				});
				$(window).on(
						{
							'resize.Scrolling' : function(event) {
								getWinSize();
								setViewportRows();
								$rows.find('a.ss-circle').removeClass(
										'ss-circle-deco');
								$rowsViewport.each(function() {
									$(this).find('div.ss-left').css({
										left : '0%'
									}).end().find('div.ss-right').css({
										right : '0%'
									}).end().find('a.ss-circle').addClass(
											'ss-circle-deco');
								});

							},
							'scroll.Scrolling' : function(event) {
								if (anim)
									return false;
								anim = true;
								setTimeout(function() {
									placeRows();
									anim = false;
								}, 10);
							}
						});
			},
			placeRows = function() {
				var winscroll = $win.scrollTop(),
				winCenter = winSize.height / 2 + winscroll;
				$rowsOutViewport
						.each(function(i) {
							var $row = $(this),
							$rowL = $row.find('div.ss-left'),
							$rowR = $row.find('div.ss-right'),
							rowT = $row.offset().top;
							if (rowT > winSize.height + winscroll) {
								if (perspective) {
									$rowL
											.css({
												'-webkit-transform' : 'translate3d(-75%, 0, 0) rotateY(-90deg) translate3d(-75%, 0, 0)',
												'opacity' : 0
											});
									$rowR
											.css({
												'-webkit-transform' : 'translate3d(75%, 0, 0) rotateY(90deg) translate3d(75%, 0, 0)',
												'opacity' : 0
											});

								} else {
									$rowL.css({
										left : '-50%'
									});
									$rowR.css({
										right : '-50%'
									});
								}
							}
							else {
								var rowH = $row.height(),
								factor = (((rowT + rowH / 2) - winCenter) / (winSize.height / 2 + rowH / 2)),
								val = Math.max(factor * 50, 0);
								if (val <= 0) {
									if (!$row.data('pointer')) {
										$row.data('pointer', true);
										$row.find('.ss-circle').addClass(
												'ss-circle-deco');
									}
								} else {
									if ($row.data('pointer')) {
										$row.data('pointer', false);
										$row.find('.ss-circle').removeClass(
												'ss-circle-deco');
									}
								}
								if (perspective) {
									var t = Math.max(factor * 75, 0), r = Math
											.max(factor * 90, 0), o = Math.min(
											Math.abs(factor - 1), 1);
									$rowL.css({
										'-webkit-transform' : 'translate3d(-'
												+ t + '%, 0, 0) rotateY(-' + r
												+ 'deg) translate3d(-' + t
												+ '%, 0, 0)',
										'opacity' : o
									});
									$rowR.css({
										'-webkit-transform' : 'translate3d('
												+ t + '%, 0, 0) rotateY(' + r
												+ 'deg) translate3d(' + t
												+ '%, 0, 0)',
										'opacity' : o
									});
								} else {
									$rowL.css({
										left : -val + '%'
									});
									$rowR.css({
										right : -val + '%'
									});
								}
							}
						});
			};
			return {
				init : init
			};
		})();
		$sidescroll.init();
	}
	$(function() {
		$('#content-time').datetimepicker({
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			currentText: '今天',
			closeText: '确定'
        });
		$("#saveOrUpdate").click(function(){
			var reg=/\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}/;
			var result=reg.exec($("#content-time").val());
			if(!result){
				alert("时间格式出错，请重新输入");
				return false;
			}
			if(!$("#content-title").val()){
				alert("标题不能为空哦，请输入");
				return false;
			}
			$.ajax({
				"url":"/content/addContent",
				"method":"post",
				"data":{
					"title":$("#content-title").val(),
					"content":$("#content-content").val(),
					"octime":$("#content-time").val(),
					"id":$("#content-id").val()
				},
				"dataType":"json",
				"async":false,
				"success":function(data){
					if(data && data.result == "success"){
						if(!data.update || $("#"+$("#content-id").val()).length<1){
							$("#myModal").modal('toggle');
							var timeRow=SMILE.generateTimeRow($("#content-time").val());
							var row =SMILE.generateRow(data.contentId?data.contentId:$("#content-id").val(),$("#content-time").val(),$("#content-title").val(),$("#content-content").val());
							if(timeRow){
								$("#ss-container").prepend(timeRow).prepend(row);
							}else{
								$("#ss-container .ss-row:eq(0)").after(row);
							}
							if(timeRow){
								SMILE.init(timeRow);
							}
							if(row){
								SMILE.init(row);
							}
							alert("插入成功!")
						}else{
							$("#myModal").modal('toggle');
							var divId=$("#content-id").val();
							var updateDiv=$("#"+divId);
							$("a",updateDiv).text($("#content-title").val());
			        		$("a",updateDiv).attr("data-content",$("#content-content").val());
			        		$("a",updateDiv).attr("data-original-title",$("#content-time").val());
							alert("修改成功!");
						}
					}else{
						alert("失败了哦！")
					}
				}
			});
		});
		$("#addContent").click(function(){
			$("#content-title").val("");
			$("#content-content").val("");
			$("#content-time").val("");
			$("#content-id").val("");
            $(".fileinput-remove-button").trigger("click");
			$("#myModal").modal('toggle');
		});
		var contentUrl = "/content/getContentByPage";
		var page=0;
		SMILE.showContent(contentUrl,page);
		$("#loadMore").click(function(){
			page++;
			SMILE.showContent(contentUrl,page);
		});
		$(window).scroll(function(){
			var scrollTop = $(this).scrollTop();
			var scrollHeight = $(document).height();
			var windowHeight = $(this).height();
			if(scrollTop + windowHeight +1 >= scrollHeight){
				page++;
				SMILE.showContent(contentUrl,page);
			}
			});
	});
})(jQuery);
