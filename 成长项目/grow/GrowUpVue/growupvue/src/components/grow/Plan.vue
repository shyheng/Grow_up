<template>
	<div>
		<el-form :inline="true" :model="formName" class="demo-form-inline">
			<el-form-item label="用户名">
				<el-input id="user5" v-model="formName.name" placeholder="请输入您的用户名"></el-input>
			</el-form-item>
			<el-button id="submit10" type="primary" @click="onSubmit">查询</el-button>
			<div>
				<el-form-item label="状态用户名">
					<div id="f1">
						<el-input  id="f2" v-model="formStatus.name" placeholder="请输入您的用户名"></el-input>
					</div>
					<div id="f3">
						<el-button id="f4" type="primary" @click="onSubmit1">查看进行</el-button>
					</div>
					<div id="f5">
						<el-input id="f6" v-model="formStatus1.name" placeholder="请输入您的用户名"></el-input>
					</div>
					<div id="f7">
						<el-button id="f8" type="primary" @click="onSubmit2">查看完成</el-button>
					</div>
				</el-form-item>
			</div>
			<el-form-item>
				<el-button id="submit11" @click="drawer = true" type="primary" style="margin-left: 16px;">
					创建新的作息
				</el-button>
				<el-button id="submit12" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
					删除错误的作息
				</el-button>
			</el-form-item>
			<el-table :data="listPlan" id="table2" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="planDate" label="具体时间">
				</el-table-column>
				<el-table-column prop="planText" label="计划内容" width="400">
				</el-table-column>
				<el-table-column prop="dayTime" label="完成天数">
				</el-table-column>
				<el-table-column prop="planStatus" label="状态">
				</el-table-column>
				<el-table-column prop="planName" label="签名">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer title="添加新计划" id="drawer1" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="a1" label="用户名">
					<el-input id="a2" v-model="formAdd.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="a3" label="开始时间">
					<input id="a4" v-model="formAdd.planDate" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="a5" label="天数">
					<el-input id="a6" v-model="formAdd.dayTime" placeholder="完成天数"></el-input>
				</el-form-item>
				<el-form-item id="a7" label="内容">
					<el-input id="a8" v-model="formAdd.planText" placeholder="请输入您的内容"></el-input>
				</el-form-item>
				<el-form-item id="a9" label="状态">
					<el-radio id="a10" v-model="formAdd.planStatus" label="0">进行</el-radio>
					<el-radio id="a10" v-model="formAdd.planStatus" label="1">完成</el-radio>
				</el-form-item>
				<el-form-item id="a11" label="签名">
					<el-input id="a12" v-model="formAdd.planName" placeholder="请您签名"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="submit13" type="primary" @click="onAdd">添加</el-button>
						<button id="submit14" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-drawer title="删除一个计划" id="drawer1" :visible.sync="drawer1" :direction="direction1"
			:before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="a13" label="用户名">
					<el-input id="a14" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="a15" label="ID">
					<el-input id="a16" v-model="formDel.id" placeholder="请输入计划的ID"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="submit15" type="primary" @click="onDel">确定删除</el-button>
						<button id="submit16" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				formName: {
					name: '',
				},
				formStatus: {
					name: '',
					status: '0',
				},
				formStatus1: {
					name: '',
					status: '1',
				},
				formAdd: {
					name: '',
					planDate: '',
					dayTime: '',
					planText: '',
					planStatus: '',
					planName: '',
				},
				formDel: {
					name: '',
					id: ''
				},
				listPlan: [],
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
				} = await this.$http.get("allPlan", {
					params: this.formName
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listPlan = res.plan;
			},
			async onSubmit1() {
				const {
					data: res
				} = await this.$http.get("status", {
					params: this.formStatus
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listPlan = res.plans;
			},
			async onSubmit2() {
				const {
					data: res
				} = await this.$http.get("status1", {
					params: this.formStatus1
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listPlan = res.plans;
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
			},
			async onAdd() {
				const {
					data: res
				} = await this.$http.get("addPlan", {
					params: this.formAdd
				});
				if (res.flag == "ok") {
					this.$message.success("添加成功")
				} else {
					this.$message.error(res.msg)
				}
			},
			async onDel() {
				const {
					data: res
				} = await this.$http.get("delPlan", {
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
	@import url(../../assets/css/grow/plan.css);
</style>
