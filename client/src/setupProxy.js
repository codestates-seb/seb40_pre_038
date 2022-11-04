const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    [
      '/api/questions',
      '/api/answer',
      '/api/search',
      '/api/tags',
      '/api/users',
      '/login',
    ],
    createProxyMiddleware({
      target: process.env.REACT_APP_API_URL, // 추후 env로 관리
      changeOrigin: true,
    })
  );
};
