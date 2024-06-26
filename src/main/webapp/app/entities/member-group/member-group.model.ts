import { IClient } from 'app/entities/client/client.model';

export interface IMemberGroup {
  id: string;
  client?: IClient | null;
}

export type NewMemberGroup = Omit<IMemberGroup, 'id'> & { id: null };
