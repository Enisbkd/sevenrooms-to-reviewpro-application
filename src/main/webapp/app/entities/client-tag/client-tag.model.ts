import { IClient } from 'app/entities/client/client.model';

export interface IClientTag {
  id: string;
  tag?: string | null;
  tagDisplay?: string | null;
  group?: string | null;
  groupDisplay?: string | null;
  color?: string | null;
  tagSearchQuery?: string | null;
  client?: IClient | null;
}

export type NewClientTag = Omit<IClientTag, 'id'> & { id: null };
