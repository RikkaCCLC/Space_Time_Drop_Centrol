import $ from 'jquery'

export default {
    state: {
        socket: null,
        sale_data_array: null,
        top5_storename: [],      //昨日top5的门店名字
        top5_sale: [],       //昨日top5的门店销量
        today_sale_num: "",        //今日总销量
        yesterday_sale_num: "",        //昨日总销量
        curmonth_sale_num: "",        //本月总销量
        lastmonth_sale_num: "",        //上月总销量
        carnum: "",          //总运营车辆
        store_num: "",       //总门店数量
        year_sale_info: [],       //年度总销售信息，实际上单凭这个已经可以实现上面几乎所有数据的初始化
        year_car_info:[],         //年度运营车辆信息
        today_year: "",              //今天是本年第几天
        today_month: "",         //今天是本月第几天
        today_week: "",          //今天是本周第几天
        option2: "",
        option4: "",
        option6: "",
        option_date_map: "",
    },
    getters: {
    },
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        updatesaleDataArray(state, sale_data_array) {
            state.sale_data_array = sale_data_array;
        },
        updateTop5StoreName(state, data) {
            let dataArray = JSON.parse(data)
            for (var i in dataArray) {
                state.top5_storename[i] = dataArray[i].stoname
            }
        },
        updateTop5Sale(state, data) {
            let dataArray = JSON.parse(data)
            for (var i in dataArray) {
                state.top5_sale[i] = dataArray[i].store_sale
            }
        },
        initSaleInfo(state, data) {
            let dataArray = JSON.parse(data)
            state.today_sale_num = dataArray[0].today_sale_num,
                state.yesterday_sale_num = dataArray[1].yesterday_sale_num,
                state.curmonth_sale_num = dataArray[2].curmonth_sale_num,
                state.lastmonth_sale_num = dataArray[3].lastmonth_sale_num,
                state.carnum = dataArray[4].carnum,
                state.store_num = dataArray[5].store_num
        },
        upSaleInfo(state) {
            state.today_sale_num += 1,
                state.curmonth_sale_num += 1
            state.option2.series[0].data[state.today_week - 1] += 1
            console.log(state.option2.series[0].data[state.today_week - 1])
        },
        downSaleInfo(state) {
            state.today_sale_num -= 1,
                state.curmonthcurweek_firstday_sale_num -= 1
            state.option2.series[0].data[state.today_week - 1] -= 1
        },
        updateYearInfo(state, year_data) {
            state.year_sale_info = JSON.parse(JSON.parse(year_data).sale_num)
            state.year_car_info = JSON.parse(JSON.parse(year_data).car_num)
            var now = new Date();
            var start = new Date(now.getFullYear(), 0, 0);
            var diff = (now - start) + ((start.getTimezoneOffset() - now.getTimezoneOffset()) * 60 * 1000);
            var oneDay = 1000 * 60 * 60 * 24;
            var dayOfYear = Math.floor(diff / oneDay);
            state.today_year = dayOfYear;

            var dayOfMonth = now.getDate();
            state.today_month = dayOfMonth;

            var dayOfWeek = now.getDay();
            state.today_week = dayOfWeek;
        },
        setOption(state) {
            let sale_data_week = [];
            let sale_data_month = [];    //长度为31

            let car_num_week = [];
            let car_num_month = []     //长度为31
            for (let i = 0; i < state.today_week; i++) {
                sale_data_week[i] = state.year_sale_info[state.today_year - state.today_week + i].count
                car_num_week[i] = state.year_car_info[state.today_year - state.today_week + i].count
            }
            for (let i = 0; i < state.today_month; i++) {
                sale_data_month[i] = state.year_sale_info[state.today_year - state.today_month + i].count
                car_num_month[i] = state.year_car_info[state.today_year - state.today_month + i].count
            }
            state.option2 = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataView: { show: true, readOnly: false },
                        magicType: { show: true, type: ['line', 'bar'] },
                        restore: { show: true },
                    }
                },
                legend: {
                    data: ['销量', '运营车辆'],
                    textStyle: {
                        color: 'rgb(255,255,255)'
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel: {
                            color: "rgba(252, 252, 252, 1)",
                        },
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '销量',
                        min: 0,
                        max: 350,
                        interval: 100,
                        axisLabel: {
                            formatter: '{value}',
                            color: "rgba(252, 252, 252, 1)",
                        },
                    },
                    {
                        type: 'value',
                        name: '运营车辆',
                        min: 500,
                        max: 3000,
                        interval: 500,
                        axisLabel: {
                            formatter: '{value}',
                            color: "rgba(252, 252, 252, 1)",
                        },
                    }
                ],
                series: [
                    {
                        name: '销量',
                        type: 'bar',
                        tooltip: {
                            valueFormatter: function (value) {
                                return value + ' 量';
                            }
                        },
                        color: 'rgb(255,255,0)',
                        data: sale_data_week,
                    },
                    {
                        name: '运营车辆',
                        type: 'line',
                        yAxisIndex: 1,
                        tooltip: {
                            valueFormatter: function (value) {
                                return value + ' 量';
                            }
                        },
                        color: 'rgb(127,255,0)',
                        data: car_num_week
                    }
                ]
            };
            state.option6 = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataView: { show: true, readOnly: false },
                        magicType: { show: true, type: ['line', 'bar'] },
                        restore: { show: true },
                    }
                },
                legend: {
                    data: ['销量', '运营车辆'],
                    textStyle: {
                        color: 'rgb(255,255,255)'
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,31],
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel: {
                            color: "rgba(252, 252, 252, 1)",
                        },
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '销量',
                        min: 0,
                        max: 350,
                        interval: 100,
                        axisLabel: {
                            formatter: '{value}',
                            color: "rgba(252, 252, 252, 1)",
                        },
                    },
                    {
                        type: 'value',
                        name: '运营车辆',
                        min: 500,
                        max: 3000,
                        interval: 500,
                        axisLabel: {
                            formatter: '{value}',
                            color: "rgba(252, 252, 252, 1)",
                        },
                    }
                ],
                series: [
                    {
                        name: '销量',
                        type: 'bar',
                        tooltip: {
                            valueFormatter: function (value) {
                                return value + ' 量';
                            }
                        },
                        data: sale_data_month
                    },
                    {
                        name: '运营车辆',
                        type: 'line',
                        yAxisIndex: 1,
                        tooltip: {
                            valueFormatter: function (value) {
                                return value + ' 量';
                            }
                        },
                        data: car_num_month
                    }
                ]
            }
        },
    },
    actions: {
        init(context) {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:34567/centrol/update_sale_data/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success() {
                    console.log("init success")
                },
                error() {
                    console.log("init error")
                }
            });
            $.ajax({
                url: "http://127.0.0.1:34567/centrol/init_saleinfo/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                async: false,  //同步
                success(resp) {
                    context.commit("initSaleInfo", resp)
                },
                error() {
                    console.log("initSaleInfo error")
                }
            });
        },
        getYearInfo(context) {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:34567/centrol/get_year_info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                async: false,  //同步
                success(resp) {
                    context.commit("updateYearInfo", resp)
                    context.commit("setOption")
                },
                error() {
                    console.log("updateYearInfo error")
                }
            });
        },
        // 获取top5相关信息
        getTop5(context) {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:34567/centrol/topfive/",
                type: "get",
                async: false,  //同步
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    context.commit("updateTop5StoreName", resp)
                    context.commit("updateTop5Sale", resp)
                },
                error() {
                    console.log("getTop5 error")
                }
            })
        },
    },
    modules: {
    }
}