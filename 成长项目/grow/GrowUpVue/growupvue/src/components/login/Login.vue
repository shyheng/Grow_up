<template>
  <el-container id="body">
    <el-header id="head">欢迎来到我的世界</el-header>
    <el-main id="main">
      <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_" label-width="0">
        <el-form-item prop="username" id="username">
          <div id="name">
            登录名
          </div>
          <el-input v-model="loginForm.username" id="user" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password" id="password">
          <div id="pwd">
            密码
          </div>
          <el-input v-model="loginForm.password" show-password id="pass" prefix-icon="el-icon-unlock" type="password"></el-input>
        </el-form-item>
        <div id="a_w">
          <el-link id="agr" type="primary" href="/agreement">注册账号</el-link>
          <el-link id="wj" type="primary" href="#">忘记密码</el-link>
        </div>
        <el-form-item class="btns">
          <el-button id="login" type="primary" @click="login()">登录</el-button>
          <el-button id="reset" tycpe="info" @click="resetLogin()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>


<script>
export default {
  data() {
    return {
      //表单数据
      loginForm: {
        username: "",
        password: ""
      },
      //验证
      loginRules: {
        //校验
        username: [
          {require: true, message: '请输入用户名', trigger: 'blur'},//必填
          {min: 3, max: 5, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        password: [
          {require: true, message: '请输入密码', trigger: 'blur'},//必填
          {min: 3, max: 10, message: '长度在6~10之间', trigger: 'blur'}//长度验证
        ]
      }
    }
  },
  methods: {
    resetLogin() {
      this.$refs.loginFormRef.resetFields()
    },
    //处理登录跳转
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;//验证失败
        const {data: res} = await this.$http.post("login", this.loginForm)//访问后台
        if (res.flag == "ok") {//判断后台发来的数据
          this.$message.success("登录成功")
          window.sessionStorage.setItem("users", res.users);
          this.$router.push({path: "/home"})
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/login/login.css";
</style>
