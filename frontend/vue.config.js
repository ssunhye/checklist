
// Vue3 관련 설정 파일
module.exports = {

  devServer: {
    compress: true,
    disableHostCheck: true,
    port: 8082,
    open: true,

    proxy: {
      '/api': {
        target: 'http://localhost:8082/'
      },
    },
    historyApiFallback: true,
    hot: true
  },

  transpileDependencies: [
    'vuex-persist',
  ],
  lintOnSave: false,
  outputDir: '../backend/src/main/resources/dist'
}
