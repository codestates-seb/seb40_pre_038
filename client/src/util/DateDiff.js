export const getYearDiff = (date) => {
  const d1 = new Date(date.slice(0, 19));
  const d2 = new Date();

  const diffDate = d1.getTime() - d2.getTime();

  return Math.floor(Math.abs(diffDate / (1000 * 60 * 60 * 24 * 365)));
};

export const getMonthDiff = (date) => {
  const d1 = new Date(date.slice(0, 19));
  const d2 = new Date();

  const diffDate = d1.getTime() - d2.getTime();

  return Math.floor(Math.abs(diffDate / (1000 * 60 * 60 * 24 * 30)));
};

export const getDateDiff = (date) => {
  const d1 = new Date(date.slice(0, 19));
  const d2 = new Date();

  const diffDate = d1.getTime() - d2.getTime();

  return Math.floor(Math.abs(diffDate / (1000 * 60 * 60 * 24)));
};
