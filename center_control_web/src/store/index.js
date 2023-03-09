import { createStore } from 'vuex'
import ModuleUser from '@/store/user'
import ModuleHomeData from '@/store/home_data'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    home_data:ModuleHomeData,
  }
})
