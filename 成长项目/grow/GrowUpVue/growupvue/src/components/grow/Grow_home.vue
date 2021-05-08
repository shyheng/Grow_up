<template>
	<div class="block">
		<el-carousel height="500px">
			<el-carousel-item v-for="item in list">
				<h3 class="small"><img :src="item.img" /></h3>
			</el-carousel-item>
		</el-carousel>
		<div id="v1">
			<div id="s1">
				联系作者,欢迎讨论学习
				<br />
				<img id="a17" src="../../assets/img/wx.png" />
				<img id="a18" src="../../assets/img/qq.jpg" />
			</div>
			<div id="s2">
				打赏区
        <br>
				<img id="a19" src="../../assets/img/wx_zf.png" />
				<img id="a20" src="../../assets/img/zfb.jpg" />
			</div>
		</div>
	</div>
</template>

<script>
	import img1 from "@/assets/img/9.jpg"
	import img2 from "@/assets/img/10.jpg"
	import img3 from "@/assets/img/24.jpg"
	import img4 from "@/assets/img/26.jpg"

	export default {
		data() {
			return {
				list: [{
						img: img1
					},
					{
						img: img2
					},
					{
						img: img3
					},
					{
						img: img4
					}
				]
			}
		}
	}
</script>

<style scoped>
	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 200px;
		margin: 0;
	}

	@import url("../../assets/css/grow/grow_home.css");
</style>
