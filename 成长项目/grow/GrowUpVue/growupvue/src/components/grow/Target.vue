<template>
	<div>
		<el-form :inline="true" :model="formName" class="demo-form-inline">
			<el-form-item label="用户名">
				<el-input id="c2" v-model="formName.name" placeholder="请输入您的用户名"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button id="btn1" type="primary" @click="onSubmit">查询</el-button>
				<el-button id="btn2" @click="drawer = true" type="primary" style="margin-left: 16px;">
					创建新的目标
				</el-button>
				<el-button id="btn3" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
					删除错误的目标
				</el-button>
			</el-form-item>
			<el-table :data="listTargets" id="table3" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="tarContent" label="具体内容">
				</el-table-column>
				<el-table-column prop="tarStart" label="开始天数">
				</el-table-column>
				<el-table-column prop="tarOver" label="结束时间" width="180">
				</el-table-column>
				<el-table-column prop="tarName" label="签名">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer id="drawer1" title="添加新目标" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="c3" label="用户名">
					<el-input id="c4" v-model="formAdd.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="c5" label="开始时间">
          <input id="c6" v-model="formAdd.tarStart" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="c7" label="结束时间">
          <input id="c8" v-model="formAdd.tarOver" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="c9" label="具体内容">
					<el-input id="c10" v-model="formAdd.tarContent" placeholder="请输入您的内容"></el-input>
				</el-form-item>
				<el-form-item id="c11" label="签名">
					<el-input id="c12" v-model="formAdd.tarName" placeholder="请您签名"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="btn4" type="primary" @click="onAdd">添加</el-button>
						<button id="btn5" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-drawer title="删除一个目标" id="drawer1" :visible.sync="drawer1" :direction="direction1"
			:before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="c13" label="用户名">
					<el-input id="c14" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="c15" label="ID">
					<el-input id="c16" v-model="formDel.id" placeholder="请输入计划的ID"></el-input>
				</el-form-item>
				<div id="button1">
					<el-form-item>
						<el-button id="btn6" type="primary" @click="onDel">确定删除</el-button>
						<button id="btn7" type="reset">重置</button>
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
				formAdd: {
					name: '',
					tarContent: '',
					tarStart: '',
					tarOver: '',
					tarName: '',
				},
				formDel: {
					name: '',
					id: ''
				},
				listTargets: [],
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
				} = await this.$http.get("allTar", {
					params: this.formName
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listTargets = res.targets;
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
				} = await this.$http.get("addTar", {
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
				} = await this.$http.get("delTar", {
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
	@import url("../../assets/css/grow/target.css");
</style>
