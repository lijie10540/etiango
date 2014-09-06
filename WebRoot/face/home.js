
        var word1 ="<div class='panel-body' id ='word1' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 今天是一个特殊的日子...</p></div>";

        var word2 ="<div class='panel-body' id ='word2' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 不知道你是否过得开心...</p></div>";

        var word3 ="<div class='panel-body' id ='word3' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 有没有好好放松一下自己...</p></div>";

        var word4 ="<div class='panel-body' id ='word4' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 我原本计划在今天突然出现在上海...</p></div>";

        var word5 ="<div class='panel-body' id ='word5' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 给你个惊喜，去第一次你带我去的餐厅，和你共进晚餐  o(∩_∩)o</p></div>";

        var word6 ="<div class='panel-body' id ='word6' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 种种原因，最后却没有成行...</p></div>";

        var word7 ="<div class='panel-body' id ='word7' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 好吧，其实是昨晚熬夜编这个程序，早上起不来了 〒_〒....</p></div>";

        var word8 ="<div class='panel-body' id ='word8' style='display:none'> "+
                   "<p style='text-align: left;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'> 不管如何，在这个特别的日子里，我想对你说...（此处省略一万六千字）</p></div>";

        var word9 ="<div class='panel-body' id ='word9' style='display:none'> "+
                   "<p style='text-align: center;'><img width='60px' height='60px' src='img/L.jpg' class='imgView'><a data-toggle='tooltip' data-placement='top' title='点击开始定位！' href = '<%=request.getContextPath()%>/main/login.do'><strong > 爱，在七夕！我特别想你。</strong></a> <img width='60px' height='60px' src='img/T.jpg' class='imgView'></p></div>";
  var home = {
         initializer:function(){
	     this.renderUI();
         this.blind();
         },
         renderUI:function(){
        	 
         },
         blind:function(){
        	 var Media = document.getElementById("media")
        	 $('#togglemodal').click(function(){$('#aboutUs').modal('toggle');});
        	 $('#navbarmenu > ul > li').each(function(item){
        		 $(this).click(function(){
        			 $('#navbarmenu > ul > li').removeClass('active');
        			 $(this).addClass('active');
        		 }); 
        		 $(this).hover(function(){
        			 if(!$(this).hasClass('active')){
        				 $('#navbarmenu > ul > li').removeClass('active');
        				 $(this).addClass('active');
        			 }
        			 if($(this).hasClass('dropdown')){
        				 $('.dropdown-menu').slideDown();
        			 }
        			 
        		 },function(){
        			 $(this).removeClass('active');
        			 if($(this).hasClass('dropdown')){
        				 $('.dropdown-menu').slideUp();
        			 }
        		 });
        	 });
        	 $("#stopmusic").click(function(){
            	 if(!Media.paused){
            		 document.getElementById("stopmusic").innerHTML ="播放音乐";
            		 Media.pause();
             	  }else{
             		 document.getElementById("stopmusic").innerHTML ="停止音乐";
            		 Media.play();
             	  }
           	     });
        	 $("#next").click(function(){
             	if(Media){
             		Media.play(); 
             		$("#stopmusic").show();
             	}
             	$("#word0").fadeOut("slow",function(){
             		    $("#word").append(word1);
             		    $("#word1").fadeIn("slow",function(){
             		    	     $("#word").append(word2);
             		    	     $("#word1").fadeOut(5000,function(){
             		    	    	 $("#word2").fadeIn("slow",function(){
             		    	    		      $("#word").append(word3);
             		    	    		          $("#word2").fadeOut(5000,function(){
             		    	    		        	      $("#word3").fadeIn("slow",function(){
             		    	    		        	    	  $("#word").append(word4);
             		    	    		        	    	  $("#word3").fadeOut(5000,function(){
             		    	    		        	    		  $("#word4").fadeIn("slow",function(){
             		    	    		        	    			     $("#word").append(word5);
             		    	    		        	    			     $("#word4").fadeOut(5000,function(){
             		    	    		        	    			    	 $("#word5").fadeIn("slow",function(){
             		    	    		        	    			    		  $("#word").append(word6);
             		    	    		        	    			    		  $("#word5").fadeOut(5000,function(){
             		    	    		        	    			    			  $("#word6").fadeIn("slow",function(){});
             		    	    		        	    			    			      $("#word").append(word7);
             		    	    		        	    			    			      $("#word6").fadeOut(5000,function(){
             		    	    		        	    			    			    	  $("#word7").fadeIn("slow",function(){
             		    	    		        	    			    			    		  $("#word").append(word8);
             		    	    		        	    			    			    		  $("#word7").fadeOut(5000,function(){
             		    	    		        	    			    			    			  $("#word8").fadeIn("slow",function(){
             		    	    		        	    			    			    				  $("#word").append(word9);
             		    	    		        	    			    			    				  $("#word9 p a").hover(function(){
                 		    	    		        	    			    			    				  $(this).css({"text-decoration":"none"});
                 		    	    		        	    			    			    				  },function(){
                     		    	    		        	    			    			    				  $(this).css({"text-decoration":"none"});
                     		    	    		        	    			    			    				  });
             		    	    		        	    			    			    				  $("#word8").fadeOut(5000,function(){
             		    	    		        	    			    			    					  $("#word9").fadeIn("slow");
             		    	    		        	    			    			    					  $("#word9 p a").tooltip();
                 		    	    		        	    			    			    				  });
                 		    	    		        	    			    			    			  });
                 		    	    		        	    			    			    		  });
                 		    	    		        	    			    			    	  });
                 		    	    		        	    			    			      });
                 		    	    		        	    			    		  });
                 		    	    		        	    			    	 });
                 		    	    		        	    			     });
                 		    	    		        	    		  });
                 		    	    		        	    	  });
                 		    	    		        	});
                 		    	    		   });
                 		    	    	 });
                 		    	  });
                 		    });
             		 });
                   });
         }
       };
       $(window).on('resize', home.initializer())