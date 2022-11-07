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
      '/api/comments',
    ],
    createProxyMiddleware({
      target: process.env.REACT_APP_HOST,
      changeOrigin: true,
    })
  );
};
