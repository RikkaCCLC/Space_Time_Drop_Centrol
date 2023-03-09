<template>
  <div>
    <h2>销售数据</h2>
    <div class="chart">
      <div>
        <table class="table" style="width:100%;text-align: center;">
        <thead class="table">
            <tr>
            <th scope="col" style="color:rgb(0,191,255)">订单编号</th>
            <th scope="col" style="color:rgb(255,255,255)">地址</th>
            <th scope="col" style="color:green">门店名字</th>
            <th scope="col" style="color:rgb(148,0,211)">交易时间</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="indent in  $store.state.home_data.sale_data_array " :key="indent.saleId">
            <td style="color:rgb(0,191,255)">{{indent.saleId}}</td>
            <td style="color:rgb(255,255,255)">{{ indent.stoAddr }}</td>
            <td style="color:green">{{indent.stoName}}</td>
            <td style="color:rgb(148,0,211)">{{indent.time}}</td>
            </tr>
        </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore();
    store.dispatch("init"); //这里只是调用了一下init，但是后端是不返回数据的

    return {
    }
  }

}
</script>

<style lang="less" scoped>
    h2{
        height:2rem;
        color:rgb(0,191,255);
        line-height: 1rem;
        text-align: center;
        font-size: 1.25rem;
        padding:0.5rem;
        margin:0.01rem;
        background-color: rgba(0,0,0,0.5);
    }
</style>>