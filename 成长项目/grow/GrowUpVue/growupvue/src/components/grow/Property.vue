<template>
	<div>
		<el-form :inline="true" :model="formName" class="demo-form-inline">
			<el-form-item label="用户名">
				<el-input id="d2" v-model="formName.name" placeholder="请输入您的用户名"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button id="bt1" type="primary" @click="onSubmit">查询</el-button>
				<el-button id="bt2" @click="drawer = true" type="primary" style="margin-left: 16px;">
					创建新的账单
				</el-button>
				<el-button id="bt3" @click="drawer1 = true" type="primary" style="margin-left: 16px;">
					删除错误的账单
				</el-button>
			</el-form-item>
			<el-table :data="listProperties" id="table4" style="width: 100%">
				<el-table-column prop="id" label="ID" width="180">
				</el-table-column>
				<el-table-column prop="propertyDate" label="具体时间">
				</el-table-column>
				<el-table-column prop="propertyType" label="类型">
				</el-table-column>
				<el-table-column prop="incomeExpenditure" label="支出或收入" width="180">
				</el-table-column>
				<el-table-column prop="propertyMoney" label="金钱数量">
				</el-table-column>
				<el-table-column prop="propertyRemarks" label="备注">
				</el-table-column>
			</el-table>
		</el-form>

		<el-drawer id="drawer2" title="添加新计划" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
			<el-form :inline="true" :model="formAdd" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="d3" label="用户名">
					<el-input id="d4" v-model="formAdd.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="d5" label="时间">
					<input id="d6" v-model="formAdd.propertyDate" type="date" placeholder="选择日期" />
				</el-form-item>
				<el-form-item id="d7" label="类型">
					<div id="d8">
					<el-radio  v-model="formAdd.propertyType" label="0">微信</el-radio>
					</div>
					<div id="d8">
					<el-radio  v-model="formAdd.propertyType" label="1">支付宝</el-radio>
					</div>
					<div id="d8">
					<el-radio v-model="formAdd.propertyType" label="2">银行卡</el-radio>
					</div>
					<div id="d8">
					<el-radio v-model="formAdd.propertyType" label="3">其他</el-radio>
					</div>
				</el-form-item>
				<el-form-item id="d9" label="收支">
					<el-radio id="d10" v-model="formAdd.incomeExpenditure" label="0">收款</el-radio>
					<el-radio id="d10" v-model="formAdd.incomeExpenditure" label="1">支付</el-radio>
				</el-form-item>
				<el-form-item id="d11" label="金钱数">
					<el-input id="d12" v-model="formAdd.propertyMoney" placeholder="数量"></el-input>
				</el-form-item>
				<el-form-item id="d13" label="备注">
					<el-input id="d14" v-model="formAdd.propertyRemarks" placeholder="备注"></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="bt4" type="primary" @click="onAdd">添加</el-button>
						<button id="bt5" type="reset">重置</button>
					</el-form-item>
				</div>
			</el-form>
		</el-drawer>
		<el-drawer title="删除一个计划" :visible.sync="drawer1" :direction="direction1" :before-close="handleClose">
			<el-form :inline="true" :model="formDel" ref="addFormRef" class="demo-form-inline">
				<el-form-item id="d15" label="用户名">
					<el-input id="d16" v-model="formDel.name" placeholder="请输入您的用户名"></el-input>
				</el-form-item>
				<el-form-item id="d17" label="ID">
					<el-input id="d18" v-model="formDel.id" placeholder="请输入计划的ID"></el-input>
				</el-form-item>
				<div>
					<el-form-item>
						<el-button id="bt6" type="primary" @click="onDel">确定删除</el-button>
						<button id="bt7" type="reset">重置</button>
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
					propertyDate: '',
					propertyType: '',
					incomeExpenditure: '',
					propertyMoney: '',
					propertyRemarks: '',
				},
				formDel: {
					name: '',
					id: ''
				},
				listProperties: [],
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
				} = await this.$http.get("allPro", {
					params: this.formName
				});
				if (res.flag == "err") return this.$message.error("没有此用户！！！");
				this.listProperties = res.properties;
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
				} = await this.$http.get("addPro", {
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
				} = await this.$http.get("delPro", {
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
	@import url("../../assets/css/grow/property.css");
</style>
