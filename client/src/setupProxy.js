const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    [
      '/api/questions',
      '/api/answers',
      '/api/search',
      '/api/tags',
      '/api/users',
      '/login',
    ],
    createProxyMiddleware({
      target: 'http://localhost:8080', // 추후 env로 관리
      changeOrigin: true,
    })
  );
};
