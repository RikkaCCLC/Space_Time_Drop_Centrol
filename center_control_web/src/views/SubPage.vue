<template>
  <div>
    <nav
      class="navbar navbar-expand-lg navbar-light"
      style="height: 2rem; width: 100%"
    >
      <div class="container">
        <a
          class="navbar-brand"
          href="/"
          style="color: white; padding: 0.5rem; height: 1rem; line-height: 1rem"
          >爱能技 数据展板</a
        >
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a
                class="nav-link active"
                aria-current="page"
                href="/home/"
                style="
                  color: white;
                  padding: 0.5rem;
                  height: 1rem;
                  line-height: 1rem;
                "
                >首页</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="/sub/"
                style="
                  color: white;
                  padding: 0.5rem;
                  height: 1rem;
                  line-height: 1rem;
                "
                >销售数据</a
              >
            </li>
          </ul>
          <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0"></ul>
            <span
              class="navbar-text"
              style="
                color: white;
                padding: 0.5rem;
                height: 1rem;
                line-height: 1rem;
              "
            >
              George
            </span>
            <li class="nav-item dropdown d-flex p-2 bd-highlight">
              <a
                class="nav-link"
                role="button"
                aria-expanded="false"
                style="
                  color: white;
                  padding: 0.5rem;
                  height: 1rem;
                  line-height: 1rem;
                "
                @click="logout"
                >登出</a
              >
            </li>
          </div>
        </div>
      </div>
    </nav>

    <!-- 设置容器 -->
    <section id="main" class="container">
      <!-- left -->
      <section class="item_left">
        <SubItemRegionLeft>
          <SubItemLeft></SubItemLeft>
        </SubItemRegionLeft>
      </section>
      <!-- right -->
      <section class="item_right">
        <SubItemRegionUp>
          <SubItemUp></SubItemUp>
        </SubItemRegionUp>
        <SubItemRegionDown>
          <SubItemDown></SubItemDown>
        </SubItemRegionDown>
      </section>
    </section>
  </div>
</template>
  
  <script>
import SubItemRegionUp from "@/components/sub/region/SubItemRegionUp.vue";
import SubItemRegionDown from "@/components/sub/region/SubItemRegionDown.vue";
import SubItemRegionLeft from "@/components/sub/region/SubItemRegionLeft.vue";
import SubItemUp from "@/components/sub/charts/SubItemUp.vue";
import SubItemDown from "@/components/sub/charts/SubItemDown.vue";

import SubItemLeft from "@/components/sub/charts/SubItemLeft.vue";
import router from '@/router/index.js'
import { useStore } from 'vuex';

export default {
  components: {
    SubItemRegionUp,
    SubItemRegionLeft,
    SubItemRegionDown,
    SubItemLeft,
    SubItemUp,
    SubItemDown,
  },
  setup() {
    const store = useStore();
    const logout = () => {
      store.dispatch("logout");
    }
    const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("legal_token", {
                token:"Bearer "+jwt_token,
                success() {
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    router.push({ name: "login" });
                    store.commit("updatePullingInfo", false);
                }
            })
        } else {
            router.push({ name: "login" });
            store.commit("updatePullingInfo", false);
        }
    return {
      logout
    };
  }
};
</script>
  
  <style lang="less">
/* 大容器样式设置 */
.container {
  min-width: 1200px;
  max-width: 2048px;
  margin: 0 auto;
  padding: 0.125rem 0.125rem 0;
  display: flex;
  .item_left {
    flex: 8;
  }
  .item_right {
    flex: 4;
  }
}
</style>