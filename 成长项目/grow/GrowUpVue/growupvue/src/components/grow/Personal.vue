<template>
	<div id="body4">
		<h1 id="head1">查看您的信息</h1>
		<el-form :inline="true" :model="formPer" class="demo-form-inline">
			<div id="form1">
				<el-form-item label="用户名">
					<el-input id="in1" v-model="formPer.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button id="bn2" type="primary" @click="onSubmit">查询</el-button>
					<el-button id="bn3" @click="drawer = true" type="primary" style="margin-left: 16px;">
						更改密码
					</el-button>
					<el-button id="bn4" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
						注销账号
					</el-button>
				</el-form-item>
			</div>
			<el-table :data="listPersonal" id="table5" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="sex" label="性别">
				</el-table-column>
				<el-table-column prop="birthday" label="生日" width="180">
				</el-table-column>
				<el-table-column prop="age" label="年龄">
				</el-table-column>
				<el-table-column prop="phone" label="手机号码">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer title="更改密码" id="drawer1" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formUp" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="e1" label="用户名">
					<el-input id="e2" v-model="formUp.username" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="e3" label="新密码">
					<el-input id="e4" v-model="formUp.password" placeholder="请输入您的新密码"></el-input>
				</el-form-item>
				<el-form-item id="e5" label="确认新密码">
					<el-input id="e6" v-model="formUp.passwordSalt" placeholder="请输入您的新密码"></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="bn5" type="primary" @click="onUp">添加</el-button>
						<button id="bn6" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-drawer title="注销账号" id="drawer1" :visible.sync="drawer1" :direction="direction1" :before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="e7" label="用户名">
					<el-input id="e8" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="e9" label="手机号码">
					<el-input id="e10" v-model="formDel.phone" placeholder="请输入您的手机号码"></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="bn7" type="primary" @click="onDel">确定删除</el-button>
						<button id="bn8" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-footer>
			<el-link id="home2" type="primary" href="/home">退出主页</el-link>
		</el-footer>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				formPer: {
					name: '',
				},
				formUp: {
					username: '',
					password: '',
					passwordSalt: '',
				},
				formDel: {
					name: '',
					phone: ''
				},
				listPersonal: [],
				drawer: false,
				direction: 'rtl',
				drawer1: false,
				direction1: 'rtl',
			}
		},
		methods: {
			async onSubmit() {
				const {
					data: res
				} = await this.$http.get("allPer", {
					params: this.formPer
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listPersonal = res.personals;
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
			},
			async onUp() {
				const {
					data: res
				} = await this.$http.get("upPer", {
					params: this.formUp
				});
				if (res.flag == "ok") {
					this.$message.success("更改成功")
				} else {
					this.$message.error(res.msg)
				}
			},
			async onDel() {
				const {
					data: res
				} = await this.$http.get("delPer", {
					params: this.formDel
				});
				if (res.flag == "ok") {
					this.$message.success("删除成功")
				} else {
					this.$message.error(res.msg)
				}
			}
		}
	}
</script>

<style scoped>
	@import url("../../assets/css/grow/personal.css");
</style>
