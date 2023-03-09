<template>
  <nav class="navbar navbar-expand-lg navbar-light" style="height: 2rem;width:100%;">
      <div class="container">
        <a class="navbar-brand" href="/" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;">爱能技销售数据展板</a>
      </div>
    </nav>
  <div class="row justify-content-md-center">
            <div class="col-3" style="margin-top: 15rem;">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label" style="color: white;font-size: 1rem;">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label" style="color: white;font-size: 1rem;">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message" style="color: red;font-size: 1rem;">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">提交</button>
                </form>
            </div>
        </div>
</template>

<script>
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from '@/router/index.js'

export default {
  setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("legal_token", {
                token:"Bearer "+jwt_token,
                success() {
                    router.push({ name: "home" });
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    store.commit("updatePullingInfo", false);
                }
            })
        } else {
            store.commit("updatePullingInfo", false);
        }

        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                  router.push({ name: 'home' });
                },
                error() {
                  error_message.value = "用户名或密码错误";
                }
            })
        }

        return {
            username,
            password,
            error_message,
            login,
        }
    }
};
</script>

<style lang="less" scoped>
.main {
  .left {
    margin: 0rem;
    flex: 4;
  }
  .right {
    margin: 0rem;
    flex: 8;
  }
}
</style>