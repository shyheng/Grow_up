<template>
	<el-container id="body3">
		<el-header>
			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
				background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router="router">
				<el-menu-item id="grow_home" index="/grow_home">
					首页
				</el-menu-item>
				<el-menu-item id="work" index="/work">
					作息
				</el-menu-item>
				<el-menu-item id="plan" index="/plan">
					计划
				</el-menu-item>
				<el-menu-item id="healthy" index="/healthy" disabled>
					健康
				</el-menu-item>
				<el-menu-item id="diary_person" index="/diary_person">
					日记
				</el-menu-item>
				<el-menu-item id="target" index="/target">
					目标
				</el-menu-item>
				<el-menu-item id="property" index="/property">
					财务
				</el-menu-item>
			</el-menu>
		</el-header>
		<el-container>
			<el-aside width="200px">
				<div id="radio">
					<el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
						<el-radio-button :label="false">展开</el-radio-button>
						<el-radio-button :label="true">收起</el-radio-button>
					</el-radio-group>
				</div>
				<el-menu default-active="1-4-1" class="el-menu-vertical-demo" id="menu" @open="handleOpen" @close="handleClose"
					:collapse="isCollapse" router="router">
					<el-menu-item index="/personal">
						<i class="el-icon-user"></i>
						<span slot="title">个人信息</span>
					</el-menu-item>
					<el-menu-item index="/all_index">
						<i class="el-icon-s-comment"></i>
						<span slot="title">表白墙</span>
					</el-menu-item>
					<a href="http://120.77.255.179:8000">
						<el-menu-item>
							<i class="el-icon-more-outline"></i>
							<span slot="title">往期版本</span>
						</el-menu-item>
					</a>
					<el-menu-item @click="open()">
						<i class="el-icon-switch-button"></i>
						<span slot="title">退出登录</span>
					</el-menu-item>
				</el-menu>
			</el-aside>
			<el-container>
				<div id="main1">
					<el-main>
						<router-view></router-view>
					</el-main>
				</div>
				<el-footer>
					<el-link id="home1" type="primary" href="/home">退出主页</el-link>
				</el-footer>
			</el-container>
		</el-container>
	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				activeIndex: '1',
				isCollapse: true
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				// console.log(key, keyPath);
			},
			handleOpen(key, keyPath) {
				// console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				// console.log(key, keyPath);
			},
			open() {
				this.$confirm('是否退出登录?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '退出成功',
					});
					this.$router.push({
						path: "/login"
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '未退出'
					});
					this.$router.push({
						path: "/grow_index"
					})
				});
			}
		}
	}
</script>

<style scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 200px;
		min-height: 400px;
	}


	a:link {
		color: aliceblue;
		text-decoration: none;
	}

	@import url("../../assets/css/grow/grow_index.css");
</style>
