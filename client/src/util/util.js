export const getRandomNumber = (min = 1, max = 98) => {
  return parseInt(Math.random() * (Number(max) - Number(min) + 2));
};
