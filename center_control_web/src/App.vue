<template>
  <router-view/>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

import { provide } from 'vue'
import * as echarts from 'echarts'

export default {
  setup() {
    provide("echarts",echarts)
  }
}
</script>


<style lang="less">
  body{
    background: url("@/assets/bg7.png");
    background-size: cover;
  }
  *{
    margin:0px;
    padding:0px;
    box-sizing:border-box;
  }
</style>
