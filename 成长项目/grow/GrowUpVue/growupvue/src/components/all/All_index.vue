<template>
	<el-container id="body2">
		<el-header id="head">表白墙</el-header>
		<el-container>
			<el-main>
				<div>
					<el-form :inline="true" :model="formName" class="demo-form-inline">
						<el-form-item>
							<el-button id="submit1" type="primary" @click="onSubmit">查看表白墙</el-button>
							<el-button id="write" @click="drawer = true" type="primary" style="margin-left: 16px;">
								写点啥
							</el-button>
						</el-form-item>
						<el-table :data="listDiary" style="width: 100%" id="table">
							<el-table-column prop="id" label="ID" width="180">
							</el-table-column>
							<el-table-column prop="dateDay" label="具体时间">
							</el-table-column>
							<el-table-column prop="text" label="计划内容" width="180">
							</el-table-column>
							<el-table-column prop="name" label="签名">
							</el-table-column>
						</el-table>
					</el-form>

					<el-drawer id="drawer" title="放飞自我" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
						<div id="form">
						<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
							<el-form-item label="内容">
								<el-input id="text" v-model="formAdd.text" placeholder="请输入您的内容"></el-input>
							</el-form-item>
							<el-form-item label="签名">
								<el-input id="name1" v-model="formAdd.name" placeholder="请签名"></el-input>
							</el-form-item>
							<div>
								<el-form-item>
									<el-button id="add" type="primary" @click="onAdd">添加</el-button>
									<button id="reset1" type="reset">重置</button>
								</el-form-item>
							</div>
						</el-form>
						</div>
					</el-drawer>
				</div>
				<el-footer>
					<el-link id="home" type="primary" href="/home">退出主页</el-link>
				</el-footer>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				formName: {},
				formAdd: {
					dateDay: new Date(),
					name: '',
					text: '',
				},
				formDel: {
					name: '',
					id: ''
				},
				listDiary: [],
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
				} = await this.$http.get("all");
				if (res.flag == "err") return this.$message.error("请稍后再试！！！");
				this.listDiary = res.diaries;
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
				} = await this.$http.get("add", {
					params: this.formAdd
				});
				if (res.flag == "ok") {
					this.$message.success("添加成功")
				} else {
					this.$message.error(res.msg)
				}
			},
		}
	}
</script>

<style scoped>
	@import url("../../assets/css/all/all.css");
</style>
