$(() => {
	getOrgAndUser();

	$("#keyword").on("keyup", (key) => {
		if (key.keyCode == 13) {
			getOrgAndUser();
		}
	});
	$("#search-btn").click(() => {
		getOrgAndUser();
	})
});

// 유저 생성
const getOrgAndUser = async () => {
	let { data: orgs } = await axios.get("/org/all");

	$("#user-tbody").empty()
	for (const org of orgs) {
		let { data: users } = await axios.get(`/user/all?orgCd=${org.orgCd}`);
		$("#user-tbody").append(getOrgTr(org, users))
	}
}

const getOrgTr = (org, users) => {
	let orgTr = "";

	orgTr += `<td class="text-center">${org.orgFullname}</td>`;
	let usersText = "";
	for (const user of users) {
		usersText += `${user.userName}(${user.userId})<br>`
	}
	if(!usersText) usersText="<span class='text-danger'>계정없음!</span>"
	orgTr += `<td class="text-center">${usersText}</td>`;
	orgTr += `<td class="text-center"><button class="btn btn-primary" onclick="createUser('${org.orgCd}')">생성</button></td>`;
	orgTr += `<td class="text-center"><button class="btn btn-warning" onclick="deleteUser('${org.orgCd}')">삭제</button></td>`;

	return `<tr>${orgTr}</tr>`;
}

const createUesrsAll = async () => {
	let { data: orgs } = await axios.get("/org/all");
	for (const org of orgs) {
		createUser(org.orgCd);
	}
	successToast("완료")
}

const createUser = async (orgCd) => {
	let { data: org } = await axios.get(`/org/${orgCd}`);
	let data = {
		userId: inko.ko2en(org.orgName),
		userName: org.orgName,
		userRole: 'MANAGER_ROLE',
		userPassword: 'qwe123!@#',
		orgCd: orgCd
	}
	try {
		await axios.post('/user/insert', data);
	} catch {
		errorToast("동일명 계정 존재!");
	}
}

const deleteUser = async (orgCd) => {
	let { data: users } = await axios.get(`/user/all?orgCd=${orgCd}`);
	for (const user of users) {
		try{
			await axios.delete(`/user/delete/${user.userId}`)
		}catch{
			errorToast("삭제실패");
		}
	}
}