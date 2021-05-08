<template>
	<div>
		<el-form :inline="true" :model="formName" class="demo-form-inline">
			<el-form-item id="b1" label="用户名">
				<el-input id="b2" v-model="formName.name" placeholder="请输入您的用户名"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button id="submit17" type="primary" @click="onSubmit">查询</el-button>
				<el-button id="submit18" @click="drawer = true" type="primary" style="margin-left: 16px;">
					创建新的日记
				</el-button>
				<el-button id="submit19" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
					删除错误的日记
				</el-button>
			</el-form-item>
			<el-table :data="listDiary" id="table1" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="diaryMood" label="心情">
				</el-table-column>
				<el-table-column prop="diaryDate" label="日期">
				</el-table-column>
				<el-table-column prop="diaryText" label="内容" width="180">
				</el-table-column>
				<el-table-column prop="diaryName" label="签名">
				</el-table-column>
				<el-table-column prop="diaryFile" label="附件">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer title="添加新日记" id="drawer1" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="b3" label="用户名">
					<el-input id="b4" v-model="formAdd.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="b5" label="心情">
					<el-input id="b6" v-model="formAdd.diaryMood" placeholder="心情怎么样"></el-input>
				</el-form-item>
				<el-form-item id="b7" label="时间">
          <input id="b8" v-model="formAdd.diaryDate" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="b9" label="内容">
					<el-input id="b10" v-model="formAdd.diaryText" placeholder="请输入您的内容"></el-input>
				</el-form-item>
				<el-form-item id="b11" label="签名">
					<el-input id="b12" v-model="formAdd.diaryName" placeholder="请您签名"></el-input>
				</el-form-item>
				<el-form-item id="b13" label="附件" disabled>
					<el-input id="b14" v-model="formAdd.diaryFile" disabled></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="submit20" type="primary" @click="onAdd">添加</el-button>
						<button id="submit21" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>

		<el-drawer title="删除一个日记" id="drawer1" :visible.sync="drawer1" :direction="direction1" :before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="b15" label="用户名">
					<el-input id="b16" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="b17" label="ID">
					<el-input id="b18" v-model="formDel.id" placeholder="请输入计划的ID"></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="submit22" type="primary" @click="onDel">确定删除</el-button>
						<button id="submit23" type="reset">重置</button>
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
					diaryMood: '',
					diaryDate: '',
					diaryName: '',
					diaryText: '',
					diaryFile: '',
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
				} = await this.$http.get("allDia", {
					params: this.formName
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listDiary = res.personalDiaries;
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
				} = await this.$http.get("addDia", {
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
				} = await this.$http.get("delDia", {
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
	@import url("../../assets/css/grow/diary_person.css");
</style>
