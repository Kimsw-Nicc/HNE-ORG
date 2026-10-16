/* jshint esversion : 6 */
$(()=>{
	getBuildings();
})

const getBuildings = async () => {
	let {data : buildings} = await axios.get(`/guide/building/all`)
	
	$("tbody").empty()
	for(const building of buildings){
		$("tbody").append(getBuildingTr(building))
	}
}

/*
const getBuildingTr = (building) => {
  let buildingTr = '';
  // building id
  buildingTr += `<input type="hidden" class="building-id" value="${building.buildingId}">`;
  // 이름
  buildingTr += createTd(`<input type="text" class="form-control building-name" size="40" value="${building.buildingName}" oninput="checkText(event)" autocomplete="off">`);
  // 순서
  buildingTr += createTd(`<input type="number" class="form-control building-sort" value="${building.buildingSort}" autocomplete="off">`);
  // 삭제
  buildingTr += createTd(`<a href="#" class="btn btn-outline-danger buidling-id remove-btn" onclick="deleteBuilding(${building.buildingId})"><i class="xi-trash"></i></a>`);
  
	return `<tr>${buildingTr}</tr>`;
}*/

function getBuildingTr(building) {
  const thumbSrc = building.buildingFilename
    ? `/resources/upload/building/${building.buildingFilename}`
    : '/resources/images/no-image.png';

  return `
    <tr data-building-id="${building.buildingId}">
      <td>
        <input type="text" class="form-control" name="buildingName" value="${building.buildingName ?? ''}" />
      </td>
      <td class="text-center">
        <div class="d-inline-flex align-items-center">
          <img src="${thumbSrc}" alt="${building.buildingOriginFilename ?? ''}"
               class="rounded mr-2" style="width:40px;height:40px;object-fit:cover;" />
          <button type="button" class="btn btn-sm btn-outline-secondary"
                  onclick="$(this).siblings('input[type=file]').click()">
            <i class="xi-camera"></i>
          </button>
          <input type="file" accept="image/*" class="d-none"
                 onchange="uploadBuildingImage(${building.buildingId}, this)" />
        </div>
      </td>
      <td>
        <input type="number" class="form-control text-center" name="buildingSort" value="${building.buildingSort ?? 0}" />
      </td>
      <td class="text-center">
        <button type="button" class="btn btn-sm btn-danger" onclick="deleteBuilding(${building.buildingId})">
          <i class="xi-trash-o"></i>
        </button>
      </td>
    </tr>
  `;
}

function uploadBuildingImage(buildingId, inputEl) {
  const file = inputEl.files[0];
  if (!file) return;

  const formData = new FormData();
  formData.append('buildingId', buildingId);
  formData.append('file', file);

  $.ajax({
    url: '/guide/building/uploadImage',
    method: 'POST',
    data: formData,
    processData: false,
    contentType: false,
    success: function (res) {
      const $row = inputEl.closest ? $(inputEl).closest('tr') : $(inputEl).closest('tr');
      $row.find('img').attr('src', `/resources/upload/building/${res.buildingFilename}?t=${Date.now()}`);
    },
    error: function () {
      alert('이미지 업로드에 실패했습니다.');
    }
  });
}

const deleteBuilding = async (buildingId) => {
	if(!confirm('삭제하시겠습니까?\n하위 안내도 및 텍스트도 삭제됩니다.')) return false;
	let params = {
		buildingId : buildingId
	}
	await axios.delete(`/guide/building/delete`, {params});
  getBuildings();
  successToast("삭제되었습니다");
}

const updateBuildings = async () => {
	let datas = []
	$("tbody").find('tr').each(function() {
		let data = {
			buildingId : $(this).find('.building-id').val(),
			buildingName : $(this).find('.building-name').val(),
			buildingSort : $(this).find('.building-sort').val(),
		}
		datas.push(data);
	})
	await axios.put(`/guide/building/updates`, datas);
	getBuildings();
	successToast("저장되었습니다");
}

const sortBuildings = async () => {
	await axios.put(`/guide/building/sort`);
  getBuildings();
  successToast("정렬되었습니다");
}

const openInsertBuilding = () => {
	$('.insert-building-name').val('');
	$('.insert-building-sort').val('');
	$('#insert-building-modal').modal('show');
}

const insertBuilding = async () => {
	if(!emptyCheck('.insert-building-name', '이름을 입력해주세요')) return false;
	if(!emptyCheck('.insert-building-sort', '순서를 입력해주세요')) return false;
	let data = {
		buildingName : $('.insert-building-name').val(),
		buildingSort : $('.insert-building-sort').val()
	}
	await axios.post(`/guide/building/insert`,data)
	getBuildings();
	successToast("추가되었습니다");
	$('#insert-building-modal').modal('hide')
}

