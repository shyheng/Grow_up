<template>
	<div>
		<el-form :inline="true" :model="formName" class="demo-form-inline">
			<el-form-item label="用户名">
				<el-input id="user3" v-model="formName.name" placeholder="请输入您的用户名"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button id="submit2" type="primary" @click="onSubmit">查询</el-button>
				<el-form-item label="查日期的用户名">
					<el-input id="user4" v-model="date.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-button id="submit3" type="primary" @click="ondate">今天作息</el-button>
				<div id="work1">
					<el-button id="submit4" @click="drawer = true" type="primary" style="margin-left: 16px;">
						创建新的作息
					</el-button>
					<el-button id="submit5" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
						删除错误的作息
					</el-button>
				</div>
			</el-form-item>
			<el-table :data="listWork" id="table1" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="workTask" label="事件" width="400">
				</el-table-column>
				<el-table-column prop="workTime" label="具体时间">
				</el-table-column>
				<el-table-column prop="workName" label="签名">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer title="添加新作息" id="drawer1" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="name2" label="用户名">
					<el-input id="name3" v-model="formAdd.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="name4" label="时间">
					<input id="name5" v-model="formAdd.workTime" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="name6" label="事件">
					<el-input id="name7" v-model="formAdd.workTask" placeholder="请输入您的事件"></el-input>
				</el-form-item>
				<el-form-item id="name8" label="签名">
					<el-input id="name9" v-model="formAdd.workName" placeholder="请您签名"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="submit6" type="primary" @click="onAdd">添加</el-button>
						<button id="submit7" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-drawer title="删除一个作息" id="drawer1" :visible.sync="drawer1" :direction="direction1"
			:before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="name10" label="用户名">
					<el-input id="name11" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="name12" label="ID">
					<el-input id="name13" v-model="formDel.id" placeholder="请输入作息的ID"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="submit8" type="primary" @click="onDel">确定删除</el-button>
						<button id="submit9" type="reset">重置</button>
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
				date: {
					name: '',
					date: new Date()
				},
				formName: {
					name: '',
				},
				formAdd: {
					name: '',
					workTime: '',
					workTask: '',
					workName: ''
				},
				formDel: {
					name: '',
					id: ''
				},
				listWork: [],
				drawer: false,
				direction: 'rtl',
				drawer1: false,
				direction1: 'rtl',
				drawer2: false,
				direction2: 'rtl',
			}
		},
		methods: {
			async onSubmit() {
				const {
					data: res
				} = await this.$http.get("allWork", {
					params: this.formName
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listWork = res.works;
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
				} = await this.$http.get("addWork", {
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
				} = await this.$http.get("delWork", {
					params: this.formDel
				});
				if (res.flag == "ok") {
					this.$message.success("删除成功")
				} else {
					this.$message.error(res.msg)
				}
			},
			async ondate() {
				const {
					data: res
				} = await this.$http.get("dateWork", {
					params: this.date
				});
				if (res.flag == "err") return this.$message.success(res.msg)
				this.listWork = res.work;
			},
		}
	}
</script>
<style scoped>
	@import url("../../assets/css/grow/work.css");
</style>
