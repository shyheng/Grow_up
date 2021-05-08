<template>
  <div id="body6">
    <h1>用户注册</h1>
    <el-form ref="formref" :model="form"  label-width="80px">
      <el-form-item id="user2" label="用户名">
        <el-input id="user1" v-model="form.username" placeholder="请输入您的用户名"></el-input>
      </el-form-item>
      <el-form-item id="pwd3" label="密码">
        <el-input id="pwd1" v-model="form.password" placeholder="请输入您的密码" show-password></el-input>
      </el-form-item>
      <el-form-item id="pwd4" label="确认密码">
        <el-input id="pwd2" v-model="form.passwordSalt" placeholder="请输入您的确认密码" show-password></el-input>
      </el-form-item>
      <el-form-item id="sex1" label="性别">
        <el-radio id="n" v-model="form.sex" label="男">男</el-radio>
        <el-radio id="v" v-model="form.sex" label="女">女</el-radio>
        <el-radio id="b" v-model="form.sex" label="保密">保密</el-radio>
      </el-form-item>
      <el-form-item id="date" label="生日">
        <el-date-picker
		id="date1"
            v-model="form.birthday"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item id="age2" label="年龄">
        <el-input id="age1" v-model="form.age" placeholder="请输入您的年龄"></el-input>
      </el-form-item>
      <el-form-item id="phone2" label="手机号码">
        <el-input id="phone1" v-model="form.phone" placeholder="请输入您的手机号码"></el-input>
      </el-form-item>
      <el-form-item class="btns1">
        <el-button id="login1" type="primary" @click="add()">注册</el-button>
        <el-button id="reset2" tycpe="info" @click="reset()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
        passwordSalt: '',
        sex: '',
        birthday: '',
        age: '',
        phone: '',
      },
      formref: {
        username: [
          {require: true, message: '请输入用户名', trigger: 'blur'},//必填
          {min: 3, max: 5, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        password: [
          {require: true, message: '请输入密码', trigger: 'blur'},//必填
          {min: 3, max: 10, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        passwordSalt: [
          {require: true, message: '请输入确认密码', trigger: 'blur'},//必填
          {min: 3, max: 10, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        sex: [
          {require: true, message: '请输入性别', trigger: 'blur'},//必填
          {min: 3, max: 5, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        birthday: [
          {require: true, message: '请输入生日', trigger: 'blur'},//必填
          {min: 3, max: 5, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        age: [
          {require: true, message: '请输入生日', trigger: 'blur'},//必填
          {min: 1, max: 3, message: '长度在3和5之间', trigger: 'blur'}//长度验证
        ],
        phone: [
          {require: true, message: '请输入手机号码', trigger: 'blur'},//必填
          {min: 11, max: 11, message: '', trigger: 'blur'}//长度验证
        ],
      }
    }
  },
  methods: {
    reset() {
      this.$refs.formref.resetFields()
    },
    add() {
      this.$refs.formref.validate(async valid => {
        if (!valid) return;//验证失败
        const {data: res} = await this.$http.get("regedit", {
          params:this.form})//访问后台
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
@import url("../../assets/css/login/regedit.css");
</style>