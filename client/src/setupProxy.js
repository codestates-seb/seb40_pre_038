const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    ['/questions', '/answer', '/search', '/tags', '/users'],
    createProxyMiddleware({
      target: 'http://localhost:8080', // 추후 env로 관리
      changeOrigin: true,
    })
  );
};
