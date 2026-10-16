/* jshint esversion : 6 */
const IS_IMG = true;
let org_pk
const openLayout = async (org_cd) => {
	org_pk = `${equipment.equipment_id}-${org_cd}`
	$('#layout-modal').modal('show')
	
	// 우선 값을 다 비우기 
	$('#staff-ready-box').html('')
	$('#staff-layout-box').html('')
	
	// dragg 가능한 class 지정
	setDraggableSnapping('.staff-layout')
	setDraggableSnapping('.team-layout')
	setDraggableSnapping('.furniture-layout')
	
	// div 값 그리기
	let teams_data = await axios.get(`/team/all?orgCd=${org_cd}`)
	let teams = teams_data.data;
	for(let team of teams){
		await getReadyTeam(team)
		await getReadyStaff(team)
	}
	getLayoutStaff()
	getLayoutTeam()
	
	$('#layout-snapping-btn').bootstrapToggle({
    on: '정밀모드 ON',
    off: '정밀모드 OFF'
  });
	
	$('#layout-snapping-btn').change(()=>{
		let snap_flag = $('#layout-snapping-btn').is(":checked")
		if(snap_flag){
			setDraggable('.staff-layout')
			setDraggable('.team-layout')
			setDraggable('.furniture-layout')
		}else{
			setDraggableSnapping('.staff-layout')
			setDraggableSnapping('.team-layout')
			setDraggableSnapping('.furniture-layout')
		}
	})
	
	$('#layout-close-btn').click(()=>{
		$('#layout-modal').modal('hide')
	})
	$('#layout-save-btn').off('click').on('click',async ()=>{
		$('#layout-save-btn').prop('disabled', true);
		try{
			await axios.delete(`/layout/delete/${org_pk}`)
			await saveStaffs()
			await saveTeams()
			successToast('저장되었습니다')
		}catch(error){
			errorToast('오류가 발생하였습니다')
		}finally{
			$('#layout-save-btn').prop('disabled', false);
		}
	})
	
	$('#layout-staff-btn').click(()=>{
		$('#layout-staff-btn').addClass('active')
		$('#layout-furniture-btn').removeClass('active')
		$('#staff-ready-box').removeClass('d-none')
		$('#furniture-ready-box').addClass('d-none')
	})
	
	
	$('#layout-furniture-btn').click(()=>{
		$('#layout-furniture-btn').addClass('active')
		$('#layout-staff-btn').removeClass('active')
		$('#staff-ready-box').addClass('d-none')
		$('#furniture-ready-box').removeClass('d-none')
	})
}


const setDraggable = (class_name) => {
	interact(class_name).unset()
  interact(class_name) // .layout 클래스가 있는 요소에 대해서만 드래그 기능을 적용합니다.
  .draggable({
    inertia: true,
    modifiers: [
      interact.modifiers.restrictRect({
        restriction: 'parent', // staff-layout-box 요소 내에서만 이동할 수 있도록 제한합니다.
        endOnly: true
      })
    ],
    autoScroll: true,
    listeners: {
      move: function (event) {
        var target = event.target;
        var x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx;
        var y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
        // 다른 레이아웃 요소와 겹치지 않는지 확인합니다.
        target.style.transform = `translate(${x}px, ${y}px)`
        target.setAttribute('data-x', x);
        target.setAttribute('data-y', y);
      }
    },
    end: function (event) {}
  })
  .on('dragend', function (event) {
    interact(event.target).on('click', function (_event) {
      _event.stopImmediatePropagation();
    }, true /* useCapture */);
  });
};

const setDraggableSnapping = (class_name) => {
	interact(class_name).unset()
	interact(class_name)
  .draggable({
    modifiers: [
      interact.modifiers.snap({
        targets: [
          interact.snappers.grid({ x: 20, y: 10 })
        ],
        range: Infinity,
        relativePoints: [ { x: 0, y: 0 } ]
      }),
      interact.modifiers.restrict({
        restriction: 'parent',
        elementRect: { top: 0, left: 0, bottom: 1, right: 1 },
        endOnly: true
      })
    ],
    inertia: true
  })
  .on('dragmove', function (event) {
  	var target = event.target;
  	var x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx;
    var y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
    target.style.transform = `translate(${x}px, ${y}px)`
  	target.setAttribute('data-x', x);
    target.setAttribute('data-y', y);
  })
}
