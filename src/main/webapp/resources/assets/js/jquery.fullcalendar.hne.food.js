/**
* Author: Coderthemes
* Component: Full-Calendar
*/
!function($) {
    "use strict";

    var CalendarApp = function() {
        this.$body = $("body")
        this.$modal = $('#event-modal'),
        this.$event = ('#external-events div.external-event'),
        this.$calendar = $('#calendar1'),
        this.$extEvents = $('#external-events'),
        this.$calendarObj = null
    };

    /* on click on event */
    CalendarApp.prototype.onEventClick =  function (calEvent, jsEvent, view) {
        var $this = this;
            var form = $("<form></form>");
            form.append("<label>식단 정보 변경</label>");
            form.append("<div class='form-group'><textarea class='form-control' name='fd_content'>" + calEvent.fd_content + "'</textarea><span class='input-group-btn'><button type='submit' class='btn btn-success waves-effect waves-light'><i class='fa fa-check'></i> Save</button></span></div>");
            $this.$modal.modal({
                backdrop: 'static'
            });
            $this.$modal.find('.delete-event').show().end().find('.save-event').hide().end().find('.modal-body').empty().prepend(form).end().find('.delete-event').unbind('click').click(function () {
                $this.$calendarObj.fullCalendar('removeEvents', function (ev) {
                    return (ev._id == calEvent._id);
                });
                $this.$modal.modal('hide');
            });
            $this.$modal.find('form').on('submit', function () {
                calEvent.title = form.find("input[type=text]").val();
                $this.$calendarObj.fullCalendar('updateEvent', calEvent);
                $this.$modal.modal('hide');
                return false;
            });
    },
    /* on select */
    CalendarApp.prototype.onSelect = function (start, end, allDay) {
        var $this = this;
            $this.$modal.modal({
                backdrop: 'static'
            });
            var form = $("<form></form>");
            form.append("<div class='row'></div>");
            form.find(".row")
            	.append("<div class='col-md-12'><div class='form-group'><input type='hidden' name='fd_cate' value='FOOD'/></div></div>")
            	.append("<div class='col-md-12'><div class='form-group'><label class='control-label'>날짜</label><input type='text' class='form-control' name='fd_day' value=''/></div></div>")
                .append("<div class='col-md-12'><div class='form-group'><label class='control-label'>식단입력</label><textarea class='form-control' placeholder='식단을 입력하세요.' rows='8' name='fd_content'></textarea></div></div>")
            $this.$modal.find('.delete-event').hide().end().find('.save-event').show().end().find('.modal-body').empty().prepend(form).end().find('.save-event').unbind('click').click(function () {
                form.submit();
            });
            $this.$modal.find('form').on('submit', function () {
                var title = form.find("input[name='fd_content']").val();
                var beginning = form.find("input[name='beginning']").val();
                var ending = form.find("input[name='ending']").val();
                var categoryClass = form.find("input[name='fd_cate']").val();
                if (title !== null) {
                    $this.$calendarObj.fullCalendar('renderEvent', {
                        title: '입력완료',
                        start:start,
                        end: end,
                        allDay: true,
                        className: categoryClass
                    }, true);  
                    $this.$modal.modal('hide');
                }
                else{
                    alert('You have to give a title to your event');
                }
                return false;
                
            });
            $this.$calendarObj.fullCalendar('unselect');
    },
    CalendarApp.prototype.enableDrag = function() {
        //init events
        $(this.$event).each(function () {
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };
            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
        });
    }
    /* Initializing */
    CalendarApp.prototype.init = function() {
        this.enableDrag();
        /*  Initialize the calendar  */
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        var form = '';
        var today = new Date($.now());

        var defaultEvents =  [{
                title: 'Hey!',
                start: new Date($.now() + 158000000),
                className: 'bg-purple'
            },
            {
                title: 'See John Deo',
                start: today,
                end: today,
                className: 'bg-success'
            },
            {
                title: 'Meet John Deo',
                start: new Date($.now() + 168000000),
                className: 'bg-info'
            },
            {
                title: 'Buy a Theme',
                start: new Date($.now() + 338000000),
                className: 'bg-primary'
            }];

        var $this = this;
        $this.$calendarObj = $this.$calendar.fullCalendar({
            slotDuration: '24:00:00', /* If we want to split day time each 15minutes */
            minTime: '00:00:00',
            maxTime: '24:00:00',
            monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
            dayNames: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
            dayNamesShort: ["일","월","화","수","목","금","토"],
            buttonText: {
               today : "오늘",
               month : "월별",
               week : "주별",
               day : "일별",
            },
            
            defaultView: 'month',  
            handleWindowResize: true,   
            height: $(window).height() - 200,   
            header: {
                left: 'prev,next today',
                center: 'title',
                right: ''
            },
            events: defaultEvents,
            editable: false,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            drop: function(date) { $this.onDrop($(this), date); },
            select: function (start, end, allDay) { $this.onSelect(start, end, allDay); },
            eventClick: function(calEvent, jsEvent, view) { $this.onEventClick(calEvent, jsEvent, view); }

        });

    },

   //init CalendarApp
    $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
    
}(window.jQuery),

//initializing CalendarApp
function($) {
    "use strict";
    $.CalendarApp.init()
}(window.jQuery);
