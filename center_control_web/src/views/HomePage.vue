<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light" style="height: 2rem;width:100%;">
      <div class="container">
        <a class="navbar-brand" href="/" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;">时空水滴科技</a>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/home/" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;">首页</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/sub/" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;">销售数据</a>
            </li>
          </ul>
          <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            </ul>
              <span class="navbar-text" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;">
                {{ currentTime }}
              </span>
              <li class="nav-item dropdown d-flex p-2 bd-highlight">
                <a class="nav-link" href="/" role="button" aria-expanded="false" style="color: white;padding:0.5rem;height: 1rem;line-height: 1rem;" @click="logout">登出</a>
              </li>
          </div>
        </div>
      </div>
    </nav>

    <!-- 大容器 -->
    <section class="container">
      <section class="left_container">
        <ItemRegion1256>
          <ItemChart1></ItemChart1>
        </ItemRegion1256>
        <ItemRegion1256>
          <ItemChart5></ItemChart5>
        </ItemRegion1256>
      </section>

      <section class="center_container">
        <ItemRegionTemp> </ItemRegionTemp>
        <ItemRegion3>
          <ItemChart3></ItemChart3>
        </ItemRegion3>
        <ItemRegion4>
          <ItemMap></ItemMap>
        </ItemRegion4>
        <ItemRegion7>
          <ItemDateMap></ItemDateMap>
        </ItemRegion7>
      </section>

      <section class="right_container">
        <ItemRegion1256>
          <ItemChart2></ItemChart2>
        </ItemRegion1256>
        <ItemRegion1256>
          <ItemChart6></ItemChart6>
        </ItemRegion1256>
      </section>
    </section>
  </div>
</template>

<script>
import ItemRegion1256 from "@/components/region/ItemRegion1256.vue";
import ItemRegion3 from "@/components/region/ItemRegion3.vue";
import ItemRegion4 from "@/components/region/ItemRegion4.vue";
import ItemRegionTemp from "@/components/region/ItemRegionTemp.vue";
import ItemRegion7 from "@/components/region/ItemRegion7.vue";

import ItemChart1 from "@/components/charts/ItemChart1.vue";
import ItemChart2 from "@/components/charts/ItemChart2.vue";
import ItemChart3 from "@/components/charts/ItemChart3.vue";
import ItemChart5 from "@/components/charts/ItemChart5.vue";
import ItemChart6 from "@/components/charts/ItemChart6.vue";
import ItemMap from "@/components/charts/ItemMap.vue";
import ItemDateMap from "@/components/charts/ItemDateMap.vue";

import { onMounted, ref, onUnmounted } from "vue";
import { useStore } from "vuex";
import router from "@/router/index.js";

export default {
  components: {
    ItemRegion1256,
    ItemRegion3,
    ItemRegion4,
    ItemRegionTemp,
    ItemRegion7,
    ItemChart1,
    ItemChart2,
    ItemChart6,
    ItemChart3,
    ItemChart5,
    ItemMap,
    ItemDateMap,
  },
  setup() {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("legal_token", {
        token: "Bearer " + jwt_token,
        success() {
          store.commit("updatePullingInfo", false);
        },
        error() {
          router.push({ name: "login" });
          store.commit("updatePullingInfo", false);
        },
      });
    } else {
      router.push({ name: "login" });
      store.commit("updatePullingInfo", false);
    }
    const currentTime = ref("");
    onMounted(() => {
      setInterval(() => {
        currentTime.value = new Date().toLocaleString();
      }, 1000);
    });
    // let $echarts = inject("echarts");
    // let my_chart2 = $echarts.init(document.getElementById("main"));
    // my_chart2.setOption(store.state.home_data.option2); 

    // 下面是websocket连接
    const socketUrl = `ws://127.0.0.1:34567/websocket/${store.state.user.token}/`;

    let socket = null;
    onMounted(() => {
      // 首先先获取初始化数据

      // 然后通过websocket连接更新数据
      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        store.commit("updateSocket", socket);
      };

      socket.onmessage = (msg) => {
        const receive = JSON.parse(msg.data);
        // 处理data
        if (receive.message === "update_sale_data") {
          store.commit("updatesaleDataArray", JSON.parse(receive.data));
        } else if (receive.message === "up") {
          store.commit("upSaleInfo");
        } else if (receive.message === "down") {
          store.commit("downSaleInfo");
        }
      };

      socket.onclose = () => {};
    });

    onUnmounted(() => {
      socket.close();
    });

    const logout = () => {
      store.dispatch("logout");
    };
    return {
      currentTime,
      logout,
    };
  },
};
</script>

<style lang="less">
.container {
  min-width: 1200px;
  max-width: 2048px;
  margin: 0 auto;
  padding: 0.125rem 0.125rem 0;
  display: flex;
  .left_container,
  .right_container {
    flex: 4;
  }
  .center_container {
    flex: 5;
  }
}
</style>